import java.util.Arrays; // Needed for Math.max, not Arrays.stream in this solution

class Solution {
    // Renamed the method from 'goldMine' to 'maxGold'
    // Removed 'static' keyword assuming it's called on an object (e.g., 'ob.maxGold(mat)')
    public int maxGold(int mat[][]) { 
        int N = mat.length;      // Number of rows
        int M = mat[0].length;   // Number of columns

        // Create a DP table to store the maximum gold collected to reach cell (i, j)
        int[][] dp = new int[N][M];

        // --- Step 1: Initialize the first column of the DP table ---
        for (int i = 0; i < N; i++) {
            dp[i][0] = mat[i][0];
        }

        // --- Step 2: Fill the DP table for subsequent columns ---
        for (int j = 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                // Possible previous paths from column (j-1) to reach (i, j):

                // Path 1: From directly left (i, j-1)
                int goldFromLeft = dp[i][j - 1];

                // Path 2: From diagonally up-left (i-1, j-1)
                int goldFromUpLeft = (i > 0) ? dp[i - 1][j - 1] : 0;

                // Path 3: From diagonally down-left (i+1, j-1)
                int goldFromDownLeft = (i < N - 1) ? dp[i + 1][j - 1] : 0;

                // The maximum gold to reach (i, j) is the gold in mat[i][j]
                // plus the maximum gold collected from any of the three possible previous cells.
                dp[i][j] = mat[i][j] + Math.max(goldFromLeft, Math.max(goldFromUpLeft, goldFromDownLeft));
            }
        }

        // --- Step 3: Find the maximum gold in the last column ---
        int maxGold = 0;
        for (int i = 0; i < N; i++) {
            maxGold = Math.max(maxGold, dp[i][M - 1]);
        }

        return maxGold;
    }
}