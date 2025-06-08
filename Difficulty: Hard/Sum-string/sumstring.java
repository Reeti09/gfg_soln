import java.math.BigInteger; // Import BigInteger for handling large numbers

class Solution {
    /**
     * Determines whether a given string s can be classified as a sum-string.
     * A sum-string is a string that can be split into more than two non-empty substrings such that:
     * 1. The rightmost substring is equal to the sum of the two substrings immediately before it (interpreted as integers).
     * 2. This condition must apply recursively to the substrings before it.
     * 3. Substrings (and any number in the sum) must not contain leading zeroes, unless the number is exactly '0'.
     *
     * @param s The input string consisting of digits.
     * @return true if s is a sum-string, false otherwise.
     */
    public boolean isSumString(String s) {
        int n = s.length();

        // Iterate through all possible lengths for the first number (s1Str)
        // len1 must be at least 1.
        // We need at least one character for s2Str and at least one for the sum.
        // So, len1 can go up to n - 2.
        for (int len1 = 1; len1 < n; len1++) {
            String s1Str = s.substring(0, len1);

            // Leading zero check for s1Str:
            // If length is greater than 1 and it starts with '0' (e.g., "01", "007"), it's invalid.
            // However, the number "0" itself is valid (e.g., "101" where '0' is num2).
            if (s1Str.length() > 1 && s1Str.charAt(0) == '0') {
                // If s1Str has an invalid leading zero, any longer s1Str starting with that '0'
                // from this position will also be invalid. So, we can break the outer loop.
                break;
            }

            // Iterate through all possible lengths for the second number (s2Str)
            // len2 must be at least 1.
            // The starting index for s2Str is len1. The end index is len1 + len2.
            // We need to ensure there's at least one character left after s2Str for the sum.
            // So, len1 + len2 must be less than n.
            for (int len2 = 1; len1 + len2 < n; len2++) {
                String s2Str = s.substring(len1, len1 + len2);

                // Leading zero check for s2Str:
                if (s2Str.length() > 1 && s2Str.charAt(0) == '0') {
                    // Similar to s1Str, if s2Str has an invalid leading zero,
                    // any longer s2Str from this position will also be invalid.
                    // So, we can break the inner loop.
                    break;
                }

                // If both s1Str and s2Str are valid based on leading zero rules,
                // we convert them to BigInteger objects to perform arithmetic.
                BigInteger num1 = new BigInteger(s1Str);
                BigInteger num2 = new BigInteger(s2Str);

                // Start the recursive verification process from the position after s2Str.
                // If this path leads to a valid sum-string, return true immediately.
                if (check(s, num1, num2, len1 + len2)) {
                    return true;
                }
            }
        }

        // If no valid sum-string split is found after trying all possible initial num1 and num2 combinations,
        // then the string is not a sum-string.
        return false;
    }

    /**
     * Recursive helper function to verify if the rest of the string 's' follows the sum-string property.
     *
     * @param s          The original input string.
     * @param num1       The first number in the current sum calculation (as BigInteger).
     * @param num2       The second number in the current sum calculation (as BigInteger).
     * @param currentIdx The starting index in 's' from where we expect the string representation of (num1 + num2).
     * @return true if the remaining part of 's' can be formed by sum-string rules, false otherwise.
     */
    private boolean check(String s, BigInteger num1, BigInteger num2, int currentIdx) {
        // Calculate the sum of the two current numbers.
        BigInteger sum = num1.add(num2);
        String sumStr = sum.toString(); // Convert the sum to its string representation.

        int sumLen = sumStr.length(); // Get the length of the sum string.

        // Check if the calculated sum string is longer than the remaining part of 's'.
        // If it is, it's impossible for 's' to be a sum-string following this path.
        if (currentIdx + sumLen > s.length()) {
            return false;
        }

        // Extract the substring from 's' that should match our calculated sum.
        String expectedSumSubstr = s.substring(currentIdx, currentIdx + sumLen);

        // If the extracted substring does not exactly match the calculated sum string,
        // this path is invalid.
        if (!expectedSumSubstr.equals(sumStr)) {
            return false;
        }

        // Base Case: If we have successfully matched the entire string 's'.
        // This means we have found a valid sequence of sums that consumed the whole string.
        // Since we started by picking at least two numbers (num1 and num2) and then generated
        // at least one sum, this guarantees "more than two non-empty substrings".
        if (currentIdx + sumLen == s.length()) {
            return true;
        }

        // Recursive Step: Continue the sum-string verification process.
        // The current 'num2' becomes the first number for the next sum.
        // The current 'sum' becomes the second number for the next sum.
        // The 'currentIdx' is advanced by the length of the sum we just matched.
        return check(s, num2, sum, currentIdx + sumLen);
    }
}