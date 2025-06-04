class Solution {
    /**
     * Calculates the length of the Longest Common Subsequence (LCS) of three strings.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @param s3 The third string.
     * @return The length of the LCS.
     */
    int lcsOf3(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int p = s3.length();

        // dp[i][j][k] stores the length of LCS of s1[0...i-1], s2[0...j-1], s3[0...k-1]
        int[][][] dp = new int[m + 1][n + 1][p + 1];

        // Fill the DP table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= p; k++) {
                    // Base Cases: If any string is empty (i.e., i=0, j=0, or k=0), LCS is 0
                    if (i == 0 || j == 0 || k == 0) {
                        dp[i][j][k] = 0;
                    }
                    // If all three characters match (s1[i-1], s2[j-1], and s3[k-1])
                    else if (s1.charAt(i - 1) == s2.charAt(j - 1) && s2.charAt(j - 1) == s3.charAt(k - 1)) {
                        dp[i][j][k] = 1 + dp[i - 1][j - 1][k - 1];
                    }
                    // If characters do not all match,
                    // take the maximum of removing one character from any of the three strings.
                    else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k],
                                        Math.max(dp[i][j - 1][k],
                                                dp[i][j][k - 1]));
                    }
                }
            }
        }

        // The length of the LCS of all three strings is stored at dp[m][n][p]
        return dp[m][n][p];
    }
}