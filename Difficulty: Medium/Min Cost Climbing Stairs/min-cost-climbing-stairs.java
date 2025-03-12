//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String[] str = br.readLine().trim().split(" ");
            int n = str.length;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            Solution sln = new Solution();
            int res = sln.minCostClimbingStairs(arr);
            System.out.println(res);
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


//Back-end complete function Template for Java

class Solution {
    static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        
        // Initialize previous two minimum costs
        int prev1 = 0;
        int prev2 = 0;
        
        for (int i = 2; i <= n; i++) {
            int current = Math.min(prev1 + cost[i - 1], prev2 + cost[i - 2]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}
