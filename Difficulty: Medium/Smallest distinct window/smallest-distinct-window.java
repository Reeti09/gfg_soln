//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String str = br.readLine();

            Solution obj = new Solution();
            System.out.println(obj.findSubString(str));

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java


class Solution {
    public int findSubString(String str) {
        if (str.length() <= 1)
            return str.length();
        
        // Step 1: Count all unique characters
        Set<Character> uniqueChars = new HashSet<>();
        for (char ch : str.toCharArray()) {
            uniqueChars.add(ch);
        }
        int totalUnique = uniqueChars.size();
        
        Map<Character, Integer> freqMap = new HashMap<>();
        int start = 0, minLength = Integer.MAX_VALUE;
        
        for (int end = 0; end < str.length(); end++) {
            char endChar = str.charAt(end);
            freqMap.put(endChar, freqMap.getOrDefault(endChar, 0) + 1);
            
            // Shrink the window until it no longer contains all unique characters
            while (freqMap.size() == totalUnique) {
                minLength = Math.min(minLength, end - start + 1);
                char startChar = str.charAt(start);
                freqMap.put(startChar, freqMap.get(startChar) - 1);
                if (freqMap.get(startChar) == 0) {
                    freqMap.remove(startChar);
                }
                start++;
            }
        }
        
        return minLength;
    }
}
