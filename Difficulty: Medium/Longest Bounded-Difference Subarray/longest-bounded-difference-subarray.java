//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;
import java.util.HashMap;


// } Driver Code Ends



class Solution {
    public ArrayList<Integer> longestSubarray(int[] arr, int x) {
        int n = arr.length;
        int left = 0, maxLength = 0, bestStart = 0;
        
        TreeMap<Integer, Integer> freqMap = new TreeMap<>(); // Stores frequency of elements

        for (int right = 0; right < n; right++) {
            freqMap.put(arr[right], freqMap.getOrDefault(arr[right], 0) + 1);

            // While max-min > x, move left pointer
            while (!freqMap.isEmpty() && freqMap.lastKey() - freqMap.firstKey() > x) {
                int leftNum = arr[left];
                freqMap.put(leftNum, freqMap.get(leftNum) - 1);
                if (freqMap.get(leftNum) == 0) freqMap.remove(leftNum);
                left++;
            }

            // Update max length & store starting index
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                bestStart = left;
            }
        }

        // Return longest subarray
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = bestStart; i < bestStart + maxLength; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}



//{ Driver Code Starts.
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
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

            int k = Integer.parseInt(br.readLine());
            // Create Solution object and find closest sum
            Solution ob = new Solution();
            ArrayList<Integer> ans = ob.longestSubarray(arr, k);

            // Print the result as a space-separated string
            for (int num : ans) {
                System.out.print(num + " ");
            }
            System.out.println(); // New line after printing the results
            System.out.println("~");
        }
    }
}

// } Driver Code Ends