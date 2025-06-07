
import java.util.HashMap; // Make sure to import HashMap

class Solution {
    public int longestCommonSum(int[] a1, int[] a2) {
        // The problem is to find the longest span (subarray) [i, j]
        // such that sum(a1[i..j]) == sum(a2[i..j]).
        // This can be rewritten as sum(a1[i..j]) - sum(a2[i..j]) == 0.
        // Let diff[k] = a1[k] - a2[k].
        // Then, the problem becomes finding the longest subarray in 'diff' array
        // whose sum is 0.

        int n = a1.length; // Both arrays have the same length as per constraints

        // Step 1: Create the 'diff' array
        // diff[k] will be 0, 1, or -1
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = a1[i] - a2[i];
        }

        // Step 2: Use a HashMap to find the longest subarray with sum zero
        // This map stores <current_prefix_sum, index_of_first_occurrence_of_this_sum>
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        
        int maxLength = 0;    // Stores the maximum length of the span found so far
        int currentSum = 0;   // Stores the current prefix sum of the 'diff' array

        // Important: Initialize sumMap with (0, -1).
        // This handles cases where the subarray with sum 0 starts from index 0 itself.
        // If currentSum becomes 0 at index 'i', it means diff[0...i] sums to 0.
        // The length would be i - (-1) = i + 1.
        sumMap.put(0, -1);

        // Step 3: Iterate through the 'diff' array
        for (int i = 0; i < n; i++) {
            currentSum += diff[i]; // Update the running prefix sum

            // If the currentSum has been seen before in the map
            if (sumMap.containsKey(currentSum)) {
                // This means the sum of elements from sumMap.get(currentSum) + 1 to i is 0.
                // Calculate the length of this span.
                int previousIndex = sumMap.get(currentSum);
                maxLength = Math.max(maxLength, i - previousIndex);
            } else {
                // If this is the first time we've encountered this currentSum,
                // store it along with its current index.
                // We only store the *first* occurrence to ensure we get the longest span.
                sumMap.put(currentSum, i);
            }
        }

        return maxLength; // Return the maximum length found
    }
}