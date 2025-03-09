//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GfG {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String s = sc.next();

            Solution obj = new Solution();

            System.out.println(obj.countPS(s));

            System.out.println("~");
        }
    }
}
// } Driver Code Ends



class Solution {
    public static int countPS(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        // Every single character is a palindrome, skip counting as per requirements
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Check substrings of length 2 or more
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                
                if (s.charAt(i) == s.charAt(j)) {
                    if (length == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "abaab";
        System.out.println(countPS(s)); // Expected Output: 3
    }
}
