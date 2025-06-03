// User function Template for Java

class Solution {

    /**
     * Helper function: Counts the number of substrings in 's' that have AT MOST 'k' distinct characters.
     * Uses the sliding window technique.
     *
     * @param s The input string.
     * @param k The maximum number of distinct characters allowed in a substring.
     * @return The count of such substrings. Returns long to handle potentially large counts.
     */
    private long countAtMostK(String s, int k) {
        // If k is negative, it's impossible to have such substrings.
        // This handles cases like when k-1 is passed and k was 1 (so k becomes 0 here).
        // A substring with 0 distinct characters is typically considered an empty string,
        // but problem context usually implies non-empty substrings, so 0 is appropriate.
        if (k < 0) {
            return 0;
        }

        long count = 0; // Total count of substrings satisfying the condition (can be very large)
        int left = 0;   // Left pointer of the sliding window

        // Frequency array for lowercase English characters ('a' through 'z')
        // charCounts[0] for 'a', charCounts[1] for 'b', ..., charCounts[25] for 'z'
        int[] charCounts = new int[26];
        int distinctCharsInWindow = 0; // Current number of distinct characters in the window s[left...right]

        // Iterate with the right pointer to expand the window
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Add the current character to the window
            // If its count was 0 before incrementing, it means it's a new distinct character
            if (charCounts[currentChar - 'a'] == 0) {
                distinctCharsInWindow++;
            }
            charCounts[currentChar - 'a']++;

            // Shrink the window from the left if the number of distinct characters exceeds 'k'
            while (distinctCharsInWindow > k) {
                char leftChar = s.charAt(left);
                charCounts[leftChar - 'a']--;
                // If the count of the character at 'left' becomes 0 after decrementing,
                // it means we've removed the last occurrence of a distinct character from the window.
                if (charCounts[leftChar - 'a'] == 0) {
                    distinctCharsInWindow--;
                }
                left++; // Move the left pointer to shrink the window
            }

            // At this point, the window s[left...right] has at most 'k' distinct characters.
            // All substrings ending at 'right' and starting from any position
            // from 'left' to 'right' (inclusive) will also have at most 'k' distinct characters.
            // The number of such substrings is (right - left + 1). Add this to the total count.
            count += (right - left + 1);
        }

        return count;
    }

    /**
     * Counts the number of substrings in 's' that have EXACTLY 'k' distinct characters.
     * Implements the principle of inclusion-exclusion:
     * count(exactly K) = count(at most K) - count(at most K-1).
     *
     * @param s The input string.
     * @param k The exact number of distinct characters required.
     * @return The count of such substrings. Note: The return type is int as per the provided signature.
     * Intermediate calculations use long to prevent overflow, and the final result is cast.
     */
    int countSubstr(String s, int k) {
        // Calculate (Number of substrings with AT MOST k distinct characters)
        long countAtMostK_val = countAtMostK(s, k);

        // Calculate (Number of substrings with AT MOST k-1 distinct characters)
        long countAtMostKMinus1_val = countAtMostK(s, k - 1);

        // The final result is their difference.
        long result = countAtMostK_val - countAtMostKMinus1_val;

        // Cast to int as per the method signature.
        // Be aware that for very large strings (N=10^6), the result could theoretically
        // exceed Integer.MAX_VALUE. However, typical competitive programming problems
        // ensure the final answer fits the specified return type.
        return (int) result;
    }
}