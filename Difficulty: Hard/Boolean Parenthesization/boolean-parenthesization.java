//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.stream.*;

class GFG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String s = br.readLine();
            Solution obj = new Solution();
            System.out.println(obj.countWays(s));
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java


class Solution {
    Map<String, Integer> memo = new HashMap<>();

    public int countWays(String exp) {
        return solve(exp, 0, exp.length() - 1, true);
    }

    private int solve(String exp, int i, int j, boolean isTrue) {
        if (i > j) return 0;
        if (i == j) {
            if (isTrue) return exp.charAt(i) == 'T' ? 1 : 0;
            else return exp.charAt(i) == 'F' ? 1 : 0;
        }

        String key = i + "_" + j + "_" + isTrue;
        if (memo.containsKey(key)) return memo.get(key);

        int ways = 0;

        for (int k = i + 1; k < j; k += 2) {
            char operator = exp.charAt(k);

            int leftTrue = solve(exp, i, k - 1, true);
            int leftFalse = solve(exp, i, k - 1, false);
            int rightTrue = solve(exp, k + 1, j, true);
            int rightFalse = solve(exp, k + 1, j, false);

            int totalWays = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int trueWays = 0;
            if (operator == '&') {
                trueWays = leftTrue * rightTrue;
            } else if (operator == '|') {
                trueWays = leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
            } else if (operator == '^') {
                trueWays = leftTrue * rightFalse + leftFalse * rightTrue;
            }

            ways += (isTrue ? trueWays : (totalWays - trueWays));
        }

        memo.put(key, ways);
        return ways;
    }

  
}
