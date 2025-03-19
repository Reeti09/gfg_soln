//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            String[] input = br.readLine().split(" ");
            int arr[] = new int[input.length];

            for (int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(input[i]);

            // Read the integer k
            int k = Integer.parseInt(br.readLine());

            // Call the solution function
            Solution obj = new Solution();
            System.out.println(obj.maxProfit(arr, k));
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {

    // Function to return max profit from k
    // transactions
    static int maxProfit(int[] prices, int k){
        int n = prices.length;
        if (n == 0 || k == 0)
            return 0;

        // DP table
        int[][][] dp = new int[n + 1][k + 1][2];

        // Iterate from last day to first
        for (int i = n - 1; i >= 0; i--) {
            for (int l = 1; l <= k; l++) {
              
                // Buy state
                dp[i][l][1] = Math.max(dp[i + 1][l][0] - prices[i], dp[i + 1][l][1]);

                // Sell state
                dp[i][l][0] = Math.max(prices[i] + dp[i + 1][l - 1][1], dp[i + 1][l][0]);
            }
        }

        return dp[0][k][1];
    }

    public static void main(String[] args){
        int k = 2;
        int[] prices = { 10, 22, 5, 80 };
        System.out.println(maxProfit(prices, k));
    }
}
