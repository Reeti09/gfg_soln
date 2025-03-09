//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String s1 = br.readLine();
            String s2 = br.readLine();

            Solution ob = new Solution();
            int ans = ob.editDistance(s1, s2);
            System.out.println(ans);

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


class Solution {
    public int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // DP Table
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize the base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;  // Deleting all characters
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;  // Inserting all characters
        }
        
        // Fill the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];  // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1],  // Replace
                        Math.min(dp[i - 1][j],  // Delete
                                 dp[i][j - 1])  // Insert
                    );
                }
            }
        }
        
        return dp[m][n];
    }
}
