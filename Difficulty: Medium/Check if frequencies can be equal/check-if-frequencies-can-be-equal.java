import java.util.HashMap;
import java.util.Map;

class Solution {
    boolean sameFreq(String s) {
        if (s == null || s.isEmpty()) {
            return true; // An empty string or null string can be considered as having equal frequencies (vacuously true)
        }

        // Step 1: Count Character Frequencies
        Map<Character, Integer> charFrequencies = new HashMap<>();
        for (char c : s.toCharArray()) {
            charFrequencies.put(c, charFrequencies.getOrDefault(c, 0) + 1);
        }

        // Step 2: Count Frequencies of Frequencies
        // This map will store: frequency_value -> count_of_characters_with_that_frequency
        Map<Integer, Integer> freqOfFrequencies = new HashMap<>();
        for (int freq : charFrequencies.values()) {
            freqOfFrequencies.put(freq, freqOfFrequencies.getOrDefault(freq, 0) + 1);
        }

        // Step 3: Analyze Frequencies
        if (freqOfFrequencies.size() == 1) {
            // Case 1: All characters already have the same frequency
            // e.g., "aabbcc" (freq {2:3}), "abc" (freq {1:3})
            return true;
        } else if (freqOfFrequencies.size() == 2) {
            // Case 2: Two distinct frequencies exist

            // Get the two frequency values and their counts
            int[] freqs = new int[2];
            int[] counts = new int[2];
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : freqOfFrequencies.entrySet()) {
                freqs[i] = entry.getKey();
                counts[i] = entry.getValue();
                i++;
            }

            // Subcase 2a: One of the frequencies is 1, and its count is 1.
            // This means one character appears once, and all others have another common frequency.
            // Removing the character with frequency 1 makes all others equal.
            // Example: "xyyzz" -> charFrequencies {'x':1, 'y':2, 'z':2}, freqOfFrequencies {1:1, 2:2}
            if ((freqs[0] == 1 && counts[0] == 1) || (freqs[1] == 1 && counts[1] == 1)) {
                return true;
            }

            // Subcase 2b: One frequency has a count of 1, and it's exactly one greater than the other frequency.
            // This means one character's frequency is (X+1), and all other characters have frequency X.
            // Removing one instance of the (X+1) character makes it X.
            // Example: "aabbccc" -> charFrequencies {'a':2, 'b':2, 'c':3}, freqOfFrequencies {2:2, 3:1}
            if ((counts[0] == 1 && freqs[0] == freqs[1] + 1) || (counts[1] == 1 && freqs[1] == freqs[0] + 1)) {
                return true;
            }

            // If it's two distinct frequencies but doesn't fit the above subcases, it's not possible
            return false;

        } else {
            // Case 3: More than two distinct frequencies exist.
            // It's impossible to make them equal by removing at most one character.
            // Example: "aaabbcde" -> {3:1, 2:1, 1:3} (frequencies 1, 2, 3 exist)
            return false;
        }
    }
}