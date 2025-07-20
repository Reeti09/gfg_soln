import java.util.HashSet;

class Solution {
    public int countValid(int n, int[] arr) {
        // Step 1: Create a set for efficient lookup of digits in arr
        boolean[] inArr = new boolean[10];
        for (int digit : arr) {
            inArr[digit] = true;
        }

        // Step 2: Calculate countComplement (digits NOT in arr)
        // and countNonZeroComplement (non-zero digits NOT in arr)
        long countComplement = 0;
        long countNonZeroComplement = 0;
        for (int i = 0; i <= 9; i++) {
            if (!inArr[i]) {
                countComplement++;
                if (i != 0) {
                    countNonZeroComplement++;
                }
            }
        }

        // Step 3: Calculate total n-digit positive integers
        // First digit can be 1-9 (9 choices)
        // Remaining n-1 digits can be 0-9 (10 choices each)
        long totalNumbers = 9 * (long) Math.pow(10, n - 1);

        // Step 4: Calculate n-digit positive integers that do NOT contain any digit from arr
        long numbersWithoutSpecificDigits;
        if (n == 1) {
            // For 1-digit numbers, it's just countNonZeroComplement
            numbersWithoutSpecificDigits = countNonZeroComplement;
        } else {
            // First digit: must be non-zero and not in arr (countNonZeroComplement choices)
            // Remaining n-1 digits: can be any digit not in arr (countComplement choices)
            numbersWithoutSpecificDigits = countNonZeroComplement * (long) Math.pow(countComplement, n - 1);
        }

        // Step 5: Subtract to find numbers with at least one specific digit
        return (int) (totalNumbers - numbersWithoutSpecificDigits);
    }
}