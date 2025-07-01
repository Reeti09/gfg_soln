import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Finds the count of all substrings of length k which have exactly k-1 distinct characters.
     *
     * @param s The input string consisting only lowercase alphabets.
     * @param k The desired length of the substrings.
     * @return The count of valid substrings.
     */
    public int substrCount(String s, int k) {
        int n = s.length();
        if (n < k) {
            return 0; // If the string is shorter than k, no substrings of length k exist.
        }

        int count = 0; // This will store the final count of valid substrings.
        
        // HashMap to store the frequency of characters in the current window.
        // Key: character, Value: frequency
        Map<Character, Integer> charFreq = new HashMap<>();
        
        // This variable keeps track of the number of distinct characters in the current window.
        int distinctCount = 0;

        // 1. Process the first window (length k)
        for (int i = 0; i < k; i++) {
            char currentChar = s.charAt(i);
            charFreq.put(currentChar, charFreq.getOrDefault(currentChar, 0) + 1);
            if (charFreq.get(currentChar) == 1) { // If this is the first time we see this character in the window
                distinctCount++;
            }
        }

        // Check the first window
        if (distinctCount == k - 1) {
            count++;
        }

        // 2. Slide the window across the rest of the string
        // The window moves from index 1 to n-k
        for (int i = k; i < n; i++) {
            // Remove the character leaving the window from the left
            char leftChar = s.charAt(i - k);
            charFreq.put(leftChar, charFreq.get(leftChar) - 1);
            if (charFreq.get(leftChar) == 0) { // If its count becomes 0, it's no longer distinct in the window
                distinctCount--;
            }

            // Add the new character entering the window from the right
            char rightChar = s.charAt(i);
            charFreq.put(rightChar, charFreq.getOrDefault(rightChar, 0) + 1);
            if (charFreq.get(rightChar) == 1) { // If this is a new distinct character in the window
                distinctCount++;
            }

            // Check if the current window satisfies the condition
            if (distinctCount == k - 1) {
                count++;
            }
        }

        return count;
    }
}