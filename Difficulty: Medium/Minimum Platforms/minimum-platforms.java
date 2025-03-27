//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            // First array input (arr)
            String[] str1 = br.readLine().trim().split(
                " "); // Read the first line and split by spaces
            int n = str1.length;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] =
                    Integer.parseInt(str1[i]); // Convert each element to an integer
            }

            // Second array input (dep)
            String[] str2 = br.readLine().trim().split(
                " "); // Read the second line and split by spaces
            int m = str2.length;
            int[] dep = new int[m];
            for (int i = 0; i < m; i++) {
                dep[i] =
                    Integer.parseInt(str2[i]); // Convert each element to an integer
            }

            Solution obj = new Solution();
            System.out.println(obj.findPlatform(arr, dep));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java



class Solution {
    // Function to find the minimum number of platforms required at the railway station
    static int findPlatform(int arr[], int dep[]) {
        int n = arr.length;

        // Step 1: Sort both arrival and departure times
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0, j = 0;
        int platformNeeded = 0;
        int maxPlatforms = 0;

        // Step 2: Use a two-pointer approach
        while (i < n && j < n) {
            if (arr[i] <= dep[j]) { // A train arrives before the previous one departs
                platformNeeded++;
                maxPlatforms = Math.max(maxPlatforms, platformNeeded);
                i++;
            } else { // A train departs before the next train arrives
                platformNeeded--;
                j++;
            }
        }

        return maxPlatforms;
    }

   
}

