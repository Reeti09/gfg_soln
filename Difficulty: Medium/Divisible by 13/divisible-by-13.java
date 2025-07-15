class Solution {
    /**
     * Checks if a large number represented as a string is divisible by 13.
     *
     * @param s A string representing the large number.
     * @return true if the number is divisible by 13, false otherwise.
     */
    public boolean divby13(String s) {
        int remainder = 0;
        int modulus = 13;

        // Iterate through each character (digit) of the input string
        for (int i = 0; i < s.length(); i++) {
            // Get the current digit as an integer
            // Character.getNumericValue converts '0' to 0, '1' to 1, etc.
            int digit = Character.getNumericValue(s.charAt(i));

            // Update the remainder using the modular arithmetic rule:
            // (previous_remainder * 10 + current_digit) % modulus
            remainder = (remainder * 10 + digit) % modulus;
        }

        // If the final remainder is 0, the number is divisible by 13
        return remainder == 0;
    }
}