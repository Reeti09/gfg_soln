//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;


// } Driver Code Ends

// User function Template for Java

class Solution {
    public int longestSubarray(int[] arr, int k) {
        int n = arr.length;
        int[] transformed = new int[n];

        // Step 1: Convert array elements: +1 if > k, -1 otherwise
        for (int i = 0; i < n; i++) {
            transformed[i] = (arr[i] > k) ? 1 : -1;
        }

        // Step 2: Use prefix sum and hashmap to find longest subarray with sum > 0
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int prefixSum = 0, maxLen = 0;
        prefixMap.put(0, -1); // Base case: prefix sum 0 at index -1

        for (int i = 0; i < n; i++) {
            prefixSum += transformed[i];

            if (prefixSum > 0) {
                maxLen = i + 1; // Whole array from 0 to i is valid
            } else if (prefixMap.containsKey(prefixSum - 1)) {
                maxLen = Math.max(maxLen, i - prefixMap.get(prefixSum - 1));
            }

            // Only store the first occurrence of a prefix sum
            prefixMap.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }
}



//{ Driver Code Starts.

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            int n = tokens.length;
            int[] arr = new int[n];

            int i = 0;
            // Parse the tokens into integers and add to the array
            for (String token : tokens) {
                arr[i] = Integer.parseInt(token);
                i++;
            }

            int k = Integer.parseInt(br.readLine().trim());
            System.out.println(new Solution().longestSubarray(arr, k));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends