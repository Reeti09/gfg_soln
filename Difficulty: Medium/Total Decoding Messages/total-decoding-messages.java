//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String digits = br.readLine().trim();
            Solution ob = new Solution();
            int ans = ob.countWays(digits);
            out.println(ans);

            out.println("~");
        }
        out.close();
    }
}

// } Driver Code Ends


// User function Template for Java
class Solution {
    public int countWays(String digits) {
        int n = digits.length();
        if (n == 0 || digits.charAt(0) == '0') return 0; // No valid decoding if first digit is '0'

        int prev2 = 1, prev1 = 1; // Base cases: Empty string and first character

        for (int i = 1; i < n; i++) {
            int curr = 0;
            int oneDigit = digits.charAt(i) - '0';  // Single digit number
            int twoDigit = Integer.parseInt(digits.substring(i - 1, i + 1)); // Two-digit number

            // If one digit (1-9) is valid, add previous value
            if (oneDigit >= 1) {
                curr += prev1;
            }

            // If two-digit (10-26) is valid, add value from two steps back
            if (twoDigit >= 10 && twoDigit <= 26) {
                curr += prev2;
            }

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    
}
