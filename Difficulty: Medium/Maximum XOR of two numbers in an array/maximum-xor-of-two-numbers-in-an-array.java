//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine();
            String[] S = s.split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(S[i]);
            }
            Solution ob = new Solution();
            System.out.println(ob.maxXor(arr));

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    // Trie Node
    static class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }
    
    // Insert number into Trie
    private void insert(TrieNode root, int num) {
        TrieNode curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (curr.child[bit] == null) {
                curr.child[bit] = new TrieNode();
            }
            curr = curr.child[bit];
        }
    }
    
    // Get max XOR for num with Trie
    private int getMaxXor(TrieNode root, int num) {
        TrieNode curr = root;
        int maxXor = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int toggledBit = 1 - bit;
            if (curr.child[toggledBit] != null) {
                maxXor |= (1 << i);
                curr = curr.child[toggledBit];
            } else {
                curr = curr.child[bit];
            }
        }
        return maxXor;
    }
    
    public int maxXor(int[] arr) {
        TrieNode root = new TrieNode();
        int maxXor = 0;
        
        insert(root, arr[0]);
        for (int i = 1; i < arr.length; i++) {
            maxXor = Math.max(maxXor, getMaxXor(root, arr[i]));
            insert(root, arr[i]);
        }
        
        return maxXor;
    }
}
