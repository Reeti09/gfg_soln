//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String S = read.readLine();

            Solution ob = new Solution();
            System.out.println(ob.longestPalindrome(S));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends



class Solution {
    static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        // Transform the string to avoid even-length palindrome issues
        StringBuilder t = new StringBuilder("#");
        for (char c : s.toCharArray()) {
            t.append(c).append("#");
        }
        String transformed = t.toString();
        int n = transformed.length();
        
        int[] P = new int[n];  // Array to store palindrome lengths
        int center = 0, right = 0;
        int maxLen = 0, start = 0;

        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;  // Mirror of current index

            // If i is within right boundary, initialize P[i] with min possible value
            if (i < right) {
                P[i] = Math.min(right - i, P[mirror]);
            }

            // Expand around the current center
            while (i + P[i] + 1 < n && i - P[i] - 1 >= 0 &&
                    transformed.charAt(i + P[i] + 1) == transformed.charAt(i - P[i] - 1)) {
                P[i]++;
            }

            // Update center and right boundary if we found a longer palindrome
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }

            // Track the longest palindrome found
            if (P[i] > maxLen) {
                maxLen = P[i];
                start = (i - maxLen) / 2;  // Convert index back to original string
            }
        }

        return s.substring(start, start + maxLen);
    }
}
