import java.util.Scanner; // Although not used directly in the Solution class, it's common for competitive programming setups

class Solution {
    /**
     * Finds the maximum possible sum of a non-empty subarray in a circular array.
     *
     * @param arr The input circular array of integers.
     * @return The maximum non-empty subarray sum, considering both non-wrapping and wrapping cases.
     */
    public int maxCircularSum(int arr[]) {
        if (arr == null || arr.length == 0) {
            // According to constraints 1 <= n, so this case might not be strictly needed,
            // but it's good practice for robustness.
            return 0; 
        }
        if (arr.length == 1) {
            return arr[0]; // If only one element, that's the max sum.
        }

        int totalSum = 0;
        
        // Variables for standard Kadane's (maximum straight subarray sum)
        int maxSoFar = arr[0];
        int currentMax = arr[0];

        // Variables for modified Kadane's (minimum straight subarray sum)
        int minSoFar = arr[0];
        int currentMin = arr[0];

        // Iterate through the array to calculate total sum, max non-wrapping sum, and min non-wrapping sum
        totalSum = arr[0]; // Initialize totalSum with the first element
        for (int i = 1; i < arr.length; i++) {
            totalSum += arr[i];

            // Update currentMax and maxSoFar for maximum subarray sum
            currentMax = Math.max(arr[i], currentMax + arr[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);

            // Update currentMin and minSoFar for minimum subarray sum
            currentMin = Math.min(arr[i], currentMin + arr[i]);
            minSoFar = Math.min(minSoFar, currentMin);
        }

        // The maximum circular sum can be either:
        // 1. The maximum sum of a non-wrapping subarray (maxSoFar).
        // 2. The total sum of the array minus the minimum sum of a non-wrapping subarray (totalSum - minSoFar).
        //    This covers the wrapping case, where the minimum sum subarray is effectively "removed" from the circle.

        // Edge case: If all numbers in the array are negative or zero,
        // minSoFar will be equal to totalSum. In this scenario, totalSum - minSoFar
        // would incorrectly result in 0. However, a non-empty subarray of all negative
        // numbers must still be negative, and the maximum would be the least negative one.
        // In such a case, maxSoFar already correctly captures this (the largest single element).
        if (totalSum == minSoFar) {
            // This condition implies all elements are non-positive (0 or negative)
            // as the minimum sum subarray is the entire array itself.
            // So, the max circular sum is simply the max non-wrapping sum (largest single element).
            return maxSoFar; 
        } else {
            // Otherwise, return the maximum of the two cases:
            // 1. Non-wrapping max sum (maxSoFar)
            // 2. Wrapping max sum (totalSum - minSoFar)
            return Math.max(maxSoFar, totalSum - minSoFar);
        }
    }
}