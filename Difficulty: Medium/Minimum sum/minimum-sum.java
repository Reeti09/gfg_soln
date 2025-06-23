import java.util.Arrays;

class Solution {
    /**
     * Forms two numbers using all digits in the given array such that their sum is minimized.
     *
     * @param arr The input array of digits (0-9).
     * @return The minimum possible sum as a string with no leading zeroes.
     */
    String minSum(int[] arr) {
        // Handle the base case where the array has only one element.
        // This addresses the `NumberFormatException` encountered previously and
        // matches the expected output for such cases (e.g., Input: [5], Output: "5").
        if (arr.length == 1) {
            return String.valueOf(arr[0]);
        }

        // Step 1: Sort the input array of digits in ascending order.
        // This is crucial because to minimize the sum, smaller digits should be placed at
        // higher place values (more to the left in the formed numbers).
        Arrays.sort(arr);

        // Step 2: Create two StringBuilder objects to build the two numbers.
        // Digits will be alternately appended to these to keep the numbers' lengths
        // as balanced as possible and ensure smaller digits contribute to higher place values.
        StringBuilder num1Sb = new StringBuilder();
        StringBuilder num2Sb = new StringBuilder();

        // Step 3: Distribute the sorted digits alternately between the two numbers.
        // This strategy ensures that the smallest available digits are used for the
        // highest place values in a balanced way, minimizing their sum.
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) { // Assign to the first number
                num1Sb.append(arr[i]);
            } else { // Assign to the second number
                num2Sb.append(arr[i]);
            }
        }

        // Step 4: Get string representations and remove any leading zeros.
        // This is essential for correct numerical addition, as "047" should be treated as 47.
        String s1 = removeLeadingZeros(num1Sb.toString());
        String s2 = removeLeadingZeros(num2Sb.toString());

        // Step 5: Perform manual string addition.
        // This avoids the overhead of BigInteger for very long numbers.
        StringBuilder sumSb = new StringBuilder();
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int carry = 0;

        // Iterate from the rightmost digits, adding them along with any carry.
        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = (i >= 0) ? (s1.charAt(i) - '0') : 0;
            int digit2 = (j >= 0) ? (s2.charAt(j) - '0') : 0;

            int currentSum = digit1 + digit2 + carry;
            sumSb.append(currentSum % 10); // Append the last digit of the sum
            carry = currentSum / 10;       // Calculate the new carry

            i--;
            j--;
        }

        // The sum is built in reverse order, so reverse it to get the correct result.
        String finalSum = sumSb.reverse().toString();

        // Step 6: Ensure the final sum string has no leading zeros, unless it's just "0".
        return removeLeadingZeros(finalSum);
    }

    /**
     * Helper method to remove leading zeros from a string representation of a number.
     * If the string is "007", it returns "7". If it's "0", it returns "0".
     * If it's an empty string or null, it returns "0" (assuming valid number context).
     */
    private String removeLeadingZeros(String s) {
        if (s == null || s.isEmpty()) {
            return "0";
        }
        int firstDigitIndex = 0;
        // Find the index of the first non-zero digit
        // Stop if we reach the last character (to handle cases like "0")
        while (firstDigitIndex < s.length() - 1 && s.charAt(firstDigitIndex) == '0') {
            firstDigitIndex++;
        }
        return s.substring(firstDigitIndex);
    }
}