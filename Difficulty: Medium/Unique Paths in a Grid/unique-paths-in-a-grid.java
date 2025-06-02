class Solution {
    // This method solves "Unique Paths with Obstacles" problem.
    // It is named 'uniquePaths' to match the provided skeleton.
    public int uniquePaths(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0; // Invalid grid
        }

        int m = grid.length;
        int n = grid[0].length;

        // If the starting cell itself is an obstacle, there are no paths.
        if (grid[0][0] == 1) {
            return 0;
        }

        // dp[i][j] will store the number of unique paths to reach cell (i, j)
        int[][] dp = new int[m][n];

        // Base case: The starting cell (0,0) has 1 path to itself.
        dp[0][0] = 1;

        // Fill the first row
        // If an obstacle is encountered, all subsequent cells in that row become unreachable from (0,0)
        for (int j = 1; j < n; j++) {
            if (grid[0][j] == 0 && dp[0][j - 1] == 1) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0; 
            }
        }

        // Fill the first column
        // If an obstacle is encountered, all subsequent cells in that column become unreachable from (0,0)
        for (int i = 1; i < m; i++) {
            if (grid[i][0] == 0 && dp[i - 1][0] == 1) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }

        // Fill the rest of the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 1) {
                    // If current cell is an obstacle, no paths can go through it
                    dp[i][j] = 0;
                } else {
                    // Sum paths from above and from left
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        // The result is the number of paths to the bottom-right corner
        return dp[m - 1][n - 1];
    }
}