import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    /**
     * Finds the minimum possible value of the string after removing exactly k characters.
     * The value is the sum of the squares of the frequencies of each distinct character.
     *
     * @param s The input string consisting of lowercase alphabets.
     * @param k The number of characters to remove.
     * @return The minimum possible value of the string.
     */
    int minValue(String s, int k) {
        // Step 1: Calculate initial frequencies of each character.
        // Using an array of size 26 for 'a' through 'z'.
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Populate a Max Priority Queue with the frequencies.
        // We use Collections.reverseOrder() to make it a Max-Heap,
        // so we can always easily get the highest frequency.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int count : freq) {
            if (count > 0) { // Only add characters that are present in the string
                pq.offer(count);
            }
        }

        // Step 3: Remove 'k' characters by repeatedly decrementing the highest frequency.
        // This is the greedy step that ensures the minimum sum of squares.
        while (k > 0 && !pq.isEmpty()) {
            int currentMaxFreq = pq.poll(); // Get the current highest frequency
            
            // Decrement the frequency (simulating removal of one character).
            currentMaxFreq--;               
            
            k--; // Decrement k, as one removal operation is done.

            if (currentMaxFreq > 0) {
                // If the frequency is still positive after decrementing, add it back to the PQ.
                // It might still be the highest or become relevant again.
                pq.offer(currentMaxFreq); 
            }
        }

        // Step 4: Calculate the final value of the string.
        // The value is the sum of the squares of the remaining frequencies.
        int resultValue = 0;
        while (!pq.isEmpty()) {
            int remainingFreq = pq.poll();
            resultValue += (remainingFreq * remainingFreq); // Add square of the frequency
        }

        return resultValue;
    }
}