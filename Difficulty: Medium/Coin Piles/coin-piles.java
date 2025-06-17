import java.util.Arrays;

class Solution {
    public int minimumCoins(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }

        // 1. Sort the array
        Arrays.sort(arr);

        // 2. Create prefix sum array
        long[] prefixSum = new long[n];
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        long minRemovals = Long.MAX_VALUE;

        // Iterate through each pile as a potential minimum threshold
        // 'i' represents the index of the current pile being considered as the minimum
        for (int i = 0; i < n; i++) {
            // Optimization: If the current pile is the same as the previous one,
            // the calculations for coins to remove from smaller piles and larger piles
            // will be identical for this 'current_min_threshold'.
            // So, we can skip redundant calculations.
            // This is crucial for performance with duplicate values in the input.
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            long currentMinThreshold = arr[i];
            long maxAllowedThreshold = currentMinThreshold + k;

            // Coins to remove from piles smaller than currentMinThreshold
            // These are piles from arr[0] to arr[i-1]
            long coinsRemovedFromSmallerPiles = (i > 0) ? prefixSum[i - 1] : 0;

            // Find the index of the first pile whose value is strictly greater than maxAllowedThreshold
            // This is equivalent to finding upper_bound in C++ STL.
            // Elements from arr[pos] to arr[n-1] are the "larger piles" that need adjustment.
            int pos = findUpperBound(arr, maxAllowedThreshold);

            long coinsRemovedFromLargerPiles = 0;
            if (pos < n) {
                // These are piles from arr[pos] to arr[n-1]
                long sumOfLargerPiles = prefixSum[n - 1] - ((pos > 0) ? prefixSum[pos - 1] : 0);
                long numberOfLargerPiles = n - pos;
                long targetSumForLargerPiles = numberOfLargerPiles * maxAllowedThreshold;
                coinsRemovedFromLargerPiles = sumOfLargerPiles - targetSumForLargerPiles;
            }

            minRemovals = Math.min(minRemovals, coinsRemovedFromSmallerPiles + coinsRemovedFromLargerPiles);
        }

        return (int) minRemovals;
    }

    /**
     * Custom binary search function to find the index of the first element
     * strictly greater than the given target.
     * If no such element exists, returns arr.length.
     * This is similar to C++'s std::upper_bound.
     *
     * @param arr    The sorted array to search in.
     * @param target The value to search for.
     * @return The index of the first element greater than target, or arr.length if no such element.
     */
    private int findUpperBound(int[] arr, long target) {
        int low = 0;
        int high = arr.length; // The search space includes the hypothetical element just past the end
        int ans = arr.length;  // Default answer if no element is greater than target

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                ans = mid;
                high = mid; // Try to find an even smaller element that is still greater
            } else {
                low = mid + 1; // arr[mid] is <= target, so we need to look in the right half
            }
        }
        return ans;
    }
}