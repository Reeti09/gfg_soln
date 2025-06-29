class Solution {
    /**
     * Checks if it's possible to split the array into 'k' or fewer contiguous subarrays
     * such that the sum of each subarray does not exceed 'maxSumLimit'.
     *
     * @param arr The input array.
     * @param k The maximum allowed number of subarrays.
     * @param maxSumLimit The maximum sum allowed for any subarray.
     * @return True if it's possible, false otherwise.
     */
    private boolean check(int[] arr, int k, long maxSumLimit) {
        long currentSum = 0;
        int numSubarrays = 1; // Start with one subarray

        for (int i = 0; i < arr.length; i++) {
            // If current element itself is greater than maxSumLimit, it's impossible to form a valid split.
            // This is a crucial check and should be considered if maxSumLimit could be very small.
            // However, our binary search 'low' is initialized to max_element, which inherently handles this.
            // Still, including it here provides robustness.
            if (arr[i] > maxSumLimit) {
                return false; 
            }

            if (currentSum + arr[i] <= maxSumLimit) {
                currentSum += arr[i];
            } else {
                // Current element cannot be added to the current subarray without exceeding maxSumLimit.
                // So, start a new subarray.
                numSubarrays++;
                currentSum = arr[i]; // The current element starts the new subarray
            }
        }
        // Return true if we managed to split it into k or fewer subarrays.
        return numSubarrays <= k;
    }

    /**
     * Finds the minimum possible maximum sum among k contiguous subarrays.
     *
     * @param arr The input array.
     * @param k The number of contiguous subarrays to split into.
     * @return The minimum possible maximum sum.
     */
    public int splitArray(int[] arr, int k) {
        long low = 0;
        long high = 0;
        int maxVal = 0; // To store the maximum single element

        // Calculate the sum of all elements and find the maximum element
        for (int num : arr) {
            high += num; // 'high' will store the total sum
            if (num > maxVal) {
                maxVal = num; // 'maxVal' will store the largest single element
            }
        }

        // The minimum possible maximum sum is at least the largest single element.
        // The maximum possible maximum sum is the sum of all elements (if k=1).
        low = maxVal; 

        long ans = high; // Initialize answer with the upper bound (a guaranteed valid sum)

        // Perform binary search on the possible range of maximum sums
        while (low <= high) {
            long mid = low + (high - low) / 2; // Calculate mid-point to avoid overflow for large low/high

            if (check(arr, k, mid)) {
                // If it's possible to achieve 'mid' as the maximum sum,
                // then 'mid' is a potential answer. Try to find a smaller one by reducing 'high'.
                ans = mid;
                high = mid - 1;
            } else {
                // If it's not possible to achieve 'mid' as the maximum sum,
                // then 'mid' is too small. We need a larger maximum sum by increasing 'low'.
                low = mid + 1;
            }
        }
        
        return (int) ans; // Cast back to int as per problem output type, assuming answer fits in int
    }
}