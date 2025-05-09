//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GfG {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int k = sc.nextInt();
            String str = sc.next();
            Solution obj = new Solution();
            System.out.println(obj.findMaximumNum(str, k));

            System.out.println("~");
        }
    }
}
// } Driver Code Ends



class Solution {
    String max;

    public String findMaximumNum(String str, int k) {
        max = str;
        helper(str.toCharArray(), k, 0);
        return max;
    }

    private void helper(char[] arr, int k, int idx) {
        if (k == 0 || idx == arr.length) return;

        int n = arr.length;
        char maxDigit = arr[idx];

        // Find the maximum digit from idx to end
        for (int i = idx + 1; i < n; i++) {
            if (arr[i] > maxDigit) {
                maxDigit = arr[i];
            }
        }

        // Reduce k only if swap is needed
        if (maxDigit != arr[idx]) {
            k--;
        }

        for (int i = n - 1; i >= idx; i--) {
            if (arr[i] == maxDigit) {
                swap(arr, idx, i);
                String curr = new String(arr);
                if (curr.compareTo(max) > 0) {
                    max = curr;
                }
                helper(arr, k, idx + 1);
                swap(arr, idx, i); // backtrack
            }
        }
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
