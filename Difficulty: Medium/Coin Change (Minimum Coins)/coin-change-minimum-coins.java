//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.lang.*;
import java.util.*;


// } Driver Code Ends

class Solution {

    public int minCoins(int coins[], int sum) {
        // Initialize the dp array with a large number (sum + 1)
        int[] dp = new int[sum + 1];
        
        // Set the initial value for the 0 sum (0 coins needed to make sum 0)
        dp[0] = 0;
        
        // Initialize other values to a large number
        for (int i = 1; i <= sum; i++) {
            dp[i] = sum + 1;
        }

        // Fill dp[] using the bottom-up approach
        for (int coin : coins) {
            for (int i = coin; i <= sum; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        // If dp[sum] is greater than sum, it means it's not possible
        return dp[sum] > sum ? -1 : dp[sum];
    }

    
}



//{ Driver Code Starts.

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            String line = br.readLine();
            String[] tokens = line.split(" ");

            // Create an ArrayList to store the integers
            ArrayList<Integer> array = new ArrayList<>();

            // Parse the tokens into integers and add to the array
            for (String token : tokens) {
                array.add(Integer.parseInt(token));
            }

            int[] arr = new int[array.size()];
            int idx = 0;
            for (int i : array) arr[idx++] = i;
            Solution obj = new Solution();
            int res = obj.minCoins(arr, k);

            System.out.println(res);

            System.out.println("~");
        }
    }
}

// } Driver Code Ends