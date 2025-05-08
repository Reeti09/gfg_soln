//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine().trim());

        Solution solution = new Solution();
        while (t-- > 0) {
            String input = reader.readLine().trim();
            String[] parts = input.split("\\s+");
            int[] arr = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();

            System.out.println(solution.findMissing(arr));

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    public int findMissing(int[] arr) {
        int n = arr.length;

        // Safely determine common difference
        int diff = (arr[1] - arr[0] == arr[n - 1] - arr[n - 2]) ? arr[1] - arr[0] : 
                   ((arr[1] - arr[0] == (arr[n - 1] - arr[0]) / n) ? arr[1] - arr[0] : 
                    arr[n - 1] - arr[n - 2]);

        if (diff == 0) return arr[0];  // All elements same (edge case)

        // Expected sum of full AP with (n + 1) elements
        long expectedSum = ((2L * arr[0] + (long) n * diff) * (n + 1)) / 2;

        long actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }

        return (int)(expectedSum - actualSum);
    }
}

