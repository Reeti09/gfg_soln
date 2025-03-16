//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
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

            System.out.println(new Solution().minJumps(arr));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends


class Solution {
    static int minJumps(int[] arr) {
        int n = arr.length;
        
        // If the array has only one element, we are already at the end
        if (n <= 1) return 0;
        
        // If the first element is zero, we can't move forward
        if (arr[0] == 0) return -1;
        
        // Initialize variables
        int maxReach = arr[0];
        int steps = arr[0];
        int jumps = 1;
        
        for (int i = 1; i < n; i++) {
            // If we've reached the end
            if (i == n - 1) return jumps;
            
            // Update the maximum reachable index
            maxReach = Math.max(maxReach, i + arr[i]);
            
            // Use a step to move forward
            steps--;
            
            // If no steps are left
            if (steps == 0) {
                jumps++;
                
                // Check if the current position is beyond the maximum reachable index
                if (i >= maxReach) return -1;
                
                // Re-initialize steps for the new range
                steps = maxReach - i;
            }
        }
        
        return -1;
    }
}
