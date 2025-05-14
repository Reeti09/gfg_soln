//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(read.readLine());

            Solution ob = new Solution();

            System.out.println(ob.countAndSay(n));

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String prev = countAndSay(n - 1);
        StringBuilder result = new StringBuilder();

        int i = 0;
        while (i < prev.length()) {
            char currentChar = prev.charAt(i);
            int count = 0;

            // Count consecutive same digits
            while (i < prev.length() && prev.charAt(i) == currentChar) {
                count++;
                i++;
            }

            result.append(count).append(currentChar);
        }

        return result.toString();
    }
}

