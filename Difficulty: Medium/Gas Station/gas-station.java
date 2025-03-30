//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {

            ArrayList<Integer> array1 = new ArrayList<Integer>();
            ArrayList<Integer> array2 = new ArrayList<Integer>();

            String line = read.readLine();
            String[] tokens = line.split(" ");
            for (String token : tokens) {
                array1.add(Integer.parseInt(token));
            }
            line = read.readLine();
            tokens = line.split(" ");
            for (String token : tokens) {
                array2.add(Integer.parseInt(token));
            }

            // ArrayList<Integer> v = new ArrayList<Integer>();
            int[] gas = new int[array1.size()];
            int idx = 0;
            for (int i : array1) gas[idx++] = i;

            int[] cost = new int[array2.size()];
            idx = 0;
            for (int i : array2) cost[idx++] = i;

            int ans = new Solution().startStation(gas, cost);

            System.out.println(ans);

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


class Solution {
    public int startStation(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0, currentGas = 0;
        int startIndex = 0;

        // Iterate through each station
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];         // Total gas available in all stations
            totalCost += cost[i];       // Total cost to travel to next stations
            currentGas += gas[i] - cost[i]; // Track the current gas balance
            
            // If currentGas goes negative, we can't continue from the current start
            if (currentGas < 0) {
                startIndex = i + 1;  // Reset the starting station to the next one
                currentGas = 0;      // Reset current gas as we are starting from the new station
            }
        }

        // If total gas is greater than or equal to total cost, return startIndex
        return totalGas >= totalCost ? startIndex : -1;
    }
}

