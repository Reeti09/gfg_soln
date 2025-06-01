import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * Counts the number of pairs (a, b) such that a is from mat1, b is from mat2,
     * and a + b = x.
     *
     * @param mat1 The first input matrix.
     * @param mat2 The second input matrix.
     * @param x    The target sum.
     * @return The number of pairs that sum up to x.
     */
    int countPairs(int[][] mat1, int[][] mat2, int x) {
        Set<Integer> mat1Elements = new HashSet<>(); // Use a HashSet to store elements from mat1
        int count = 0; // Initialize the counter for valid pairs

        // Step 1: Iterate through mat1 and add all its elements to the HashSet.
        // This allows for quick lookups later.
        for (int[] row : mat1) {
            for (int element : row) {
                mat1Elements.add(element);
            }
        }

        // Step 2: Iterate through mat2. For each element, calculate the complement
        // needed to reach 'x' and check if that complement exists in mat1Elements.
        for (int[] row : mat2) {
            for (int element : row) {
                // Calculate the value 'a' that we need from mat1 such that 'a + element = x'
                int complement = x - element;

                // Check if this 'complement' exists in our set of mat1 elements
                if (mat1Elements.contains(complement)) {
                    count++; // If it exists, we found a valid pair
                }
            }
        }

        return count; // Return the total count of such pairs
    }
}