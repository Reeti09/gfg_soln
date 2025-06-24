import java.lang.StringBuilder; // Explicit import, though often not needed for java.lang classes

class Solution {
    /**
     * Finds the lexicographically largest string that can be formed by deleting exactly K characters from S.
     * This is a classic monotonic stack problem.
     *
     * @param s The input string.
     * @param k The number of characters to delete.
     * @return The lexicographically largest string after K deletions.
     */
    public static String maxSubseq(String s, int k) {
        // Use a StringBuilder to efficiently build the result string.
        // It acts as a stack where we can append (push) and delete last character (pop).
        StringBuilder result = new StringBuilder();

        // Iterate through each character of the input string 's'
        for (char currentChar : s.toCharArray()) {
            // This 'while' loop is the core of the greedy strategy.
            // We want to remove smaller characters that appear before a larger character,
            // as long as we have deletions 'k' remaining.
            while (k > 0 && result.length() > 0 && result.charAt(result.length() - 1) < currentChar) {
                // The last character in 'result' (stack top) is smaller than 'currentChar'.
                // Removing it will allow 'currentChar' to appear earlier, potentially
                // making the string lexicographically larger.
                result.deleteCharAt(result.length() - 1); // "Pop" the smaller character
                k--; // Decrement the count of remaining deletions
            }
            // After potentially removing smaller characters, append the 'currentChar'.
            // This 'currentChar' is either larger than what was previously at the end of 'result'
            // or 'k' ran out, or 'result' was empty, or 'currentChar' was smaller.
            result.append(currentChar);
        }

        // After processing all characters from 's', we might still have 'k' deletions left.
        // This happens if the characters added to 'result' were already in non-increasing order
        // (e.g., "zyxw"). In such cases, to meet the 'exactly K deletions' requirement,
        // we must remove the remaining 'k' characters from the end of the 'result' string.
        while (k > 0 && result.length() > 0) {
            result.deleteCharAt(result.length() - 1); // Remove from the end
            k--; // Decrement deletions
        }

        // Final check: If after all operations, the result string is empty, return an empty string.
        // This can happen if k was greater than or equal to the original string's length.
        if (result.length() == 0) {
            return "";
        }

        // Convert the StringBuilder content to a String and return.
        return result.toString();
    }
}