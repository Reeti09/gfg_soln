import java.util.Arrays; // Not strictly necessary for this specific code, but a common import.

class Solution {

    // Helper class to store the length and the minimum sum of an LIS
    // that ends at a particular index.
    static class LISInfo {
        int length;   // Length of the LIS
        long minSum;  // Minimum sum of elements for an LIS of this length. Using long for sum to prevent overflow.

        public LISInfo(int length, long minSum) {
            this.length = length;
            this.minSum = minSum;
        }
    }

    /**
     * Finds the maximum possible sum of all elements that are not part of the
     * Longest Increasing Subsequence (LIS). This is achieved by finding the LIS
     * that has the minimum possible sum.
     *
     * @param arr The input array of positive integers.
     * @return The maximum possible sum of elements not part of an LIS.
     */
    public int nonLisMaxSum(int[] arr) {
        int n = arr.length;

        // Handle edge cases for empty or single-element arrays
        if (n == 0) {
            return 0; // No elements, so sum of non-LIS is 0
        }
        if (n == 1) {
            // If there's only one element, that element itself is the LIS.
            // There are no elements NOT part of the LIS.
            return 0;
        }

        // Calculate the total sum of all elements in the array.
        // Use 'long' to prevent potential integer overflow, as sum of positive integers can be large.
        long totalSum = 0;
        for (int x : arr) {
            totalSum += x;
        }

        // dp[i] will store LISInfo (length and minSum) for an LIS ending at arr[i].
        LISInfo[] dp = new LISInfo[n];

        // Initialize the dp array.
        // Each element arr[i] itself forms an LIS of length 1 with a sum equal to arr[i].
        for (int i = 0; i < n; i++) {
            dp[i] = new LISInfo(1, arr[i]);
        }

        // Fill the dp table using nested loops.
        // dp[i] represents the LIS ending at arr[i].
        // For each arr[i], we try to extend previous LISs ending at arr[j] (where j < i).
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If arr[j] can precede arr[i] in an increasing subsequence
                if (arr[j] < arr[i]) {
                    int currentLen = dp[j].length + 1;
                    long currentSum = dp[j].minSum + arr[i]; // Calculate intermediate sum using long

                    // Case 1: Found a longer LIS ending at arr[i]
                    if (currentLen > dp[i].length) {
                        dp[i].length = currentLen;
                        dp[i].minSum = currentSum;
                    }
                    // Case 2: Found an LIS of the same length, but with a smaller sum
                    else if (currentLen == dp[i].length) {
                        dp[i].minSum = Math.min(dp[i].minSum, currentSum);
                    }
                }
            }
        }

        // After filling the dp table, find the overall maximum LIS length
        // and the minimum sum corresponding to that maximum length.
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, dp[i].length);
        }

        long minSumOfLIS = Long.MAX_VALUE; // Initialize with a very large value for finding minimum
        // Iterate through dp array again to find the minimum sum among all LIS of 'maxLength'
        for (int i = 0; i < n; i++) {
            if (dp[i].length == maxLength) {
                minSumOfLIS = Math.min(minSumOfLIS, dp[i].minSum);
            }
        }

        // The maximum sum of elements NOT part of LIS is the Total Sum minus the minimum sum of LIS.
        // Cast the final result to int as per the method signature. This assumes the final result fits in an int.
        return (int) (totalSum - minSumOfLIS);
    }
}