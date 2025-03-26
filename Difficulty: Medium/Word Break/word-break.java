//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class GfG {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while (t-- > 0) {
            String s = sc.nextLine();
            String line = sc.nextLine();
            String[] dictionary = line.split(" ");

            Solution obj = new Solution();
            if (obj.wordBreak(s, dictionary)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
            System.out.println("~");
        }
    }
}
// } Driver Code Ends



class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

class Solution {
    TrieNode root = new TrieNode(); // Trie root node
    Map<Integer, Boolean> memo = new HashMap<>(); // Memoization map

    // Insert words into Trie
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }

    // Check if the string can be broken using Trie + Memoization
    private boolean wordBreakHelper(String s, int start) {
        if (start == s.length()) return true; // Entire string matched
        if (memo.containsKey(start)) return memo.get(start); // Check memo

        TrieNode node = root;
        for (int end = start; end < s.length(); end++) {
            char c = s.charAt(end);
            if (!node.children.containsKey(c)) break; // No match in Trie
            node = node.children.get(c);
            if (node.isEndOfWord && wordBreakHelper(s, end + 1)) {
                memo.put(start, true);
                return true;
            }
        }
        memo.put(start, false);
        return false;
    }

    public boolean wordBreak(String s, String[] dictionary) {
        for (String word : dictionary) insert(word); // Build Trie
        return wordBreakHelper(s, 0); // Start recursion
    }

    
}
