import java.util.ArrayList;
import java.util.List; // Using List interface for flexibility, converting to ArrayList at the end

class Solution {

    // DP table to store palindrome status: dp[i][j] is true if s[i...j] is a palindrome
    // This is declared as an instance variable to be accessible by backtrack method
    private boolean[][] dp;

    // The input string itself
    private String s;

    // Length of the string
    private int n;

    // Stores all valid palindromic partitions found
    // Using ArrayList of ArrayLists as per the return type requirement
    private ArrayList<ArrayList<String>> result;

    // Stores the current partition being built during backtracking
    private ArrayList<String> currentPartition;


    /**
     * Finds all possible ways to partition a string such that every substring in the partition is a palindrome.
     *
     * @param s The input string.
     * @return An ArrayList of ArrayLists, where each inner ArrayList represents a valid palindromic partition.
     */
    public ArrayList<ArrayList<String>> palinParts(String s) {
        this.s = s;
        this.n = s.length();

        // Initialize the result list and the current partition list
        this.result = new ArrayList<>();
        this.currentPartition = new ArrayList<>();

        // Handle empty string case (though constraints usually prevent this for non-empty string problems)
        if (n == 0) {
            // An empty string has one partition: an empty list of strings.
            // Some problems might expect [[ "" ]] for a single empty string partition,
            // but for "every substring", an empty string has no substrings.
            // Usually, it's just an empty outer list or [[ ]] if 0 length is input.
            // Based on example output [g, ee, k, s], this implies non-empty substrings.
            return result; 
        }

        // Step 1: Precompute all palindromic substrings using dynamic programming.
        // dp[i][j] will be true if substring s[i...j] is a palindrome.
        dp = new boolean[n][n];

        // All single characters are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Check palindromes of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }

        // Check palindromes of length 3 or more
        // 'length' represents the current substring length we are checking
        for (int length = 3; length <= n; length++) {
            // 'i' is the starting index of the substring
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1; // 'j' is the ending index of the substring

                // A substring s[i...j] is a palindrome if:
                // 1. Its end characters s[i] and s[j] are the same.
                // 2. The inner substring s[i+1...j-1] is also a palindrome.
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        // Step 2: Start the backtracking process from the beginning of the string (index 0).
        backtrack(0);

        // Return the list of all found palindromic partitions.
        return result;
    }

    /**
     * Recursive backtracking helper function to find partitions.
     *
     * @param start_index The starting index for the current segment of the string to partition.
     */
    private void backtrack(int start_index) {
        // Base Case: If start_index reaches the end of the string,
        // it means we have successfully partitioned the entire string into palindromes.
        if (start_index == n) {
            // Add a new ArrayList containing a copy of the current partition to the results.
            // It's crucial to add a copy, as currentPartition will be modified during backtracking.
            result.add(new ArrayList<>(currentPartition)); 
            return;
        }

        // Iterate through all possible ending positions (i) for the current substring.
        // The substring considered will be s[start_index ... i].
        for (int i = start_index; i < n; i++) {
            // Check if the current substring s[start_index ... i] is a palindrome.
            // We use the precomputed DP table for O(1) lookup.
            if (dp[start_index][i]) {
                // If it's a palindrome, add it to our current partition.
                currentPartition.add(s.substring(start_index, i + 1));

                // Recursively call backtrack for the remaining part of the string,
                // starting from the character after the current palindrome (i + 1).
                backtrack(i + 1);

                // Backtrack step: Remove the last added substring.
                // This is essential to explore other partition possibilities.
                // For example, if "aa" is a palindrome, we can partition as ["a", "a"] or ["aa"].
                // After exploring ["a", ...] we remove "a" to try ["aa", ...].
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }
}
