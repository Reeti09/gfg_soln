import java.util.HashMap;
import java.util.Map;

class Solution {
    public int vowelCount(String s) {
        // Step 1: Identify Unique Vowels and Their Counts
        int[] counts = new int[5]; // 0:a, 1:e, 2:i, 3:o, 4:u
        int numUniqueVowelTypes = 0;

        for (char c : s.toCharArray()) {
            if (c == 'a') {
                if (counts[0] == 0) numUniqueVowelTypes++;
                counts[0]++;
            } else if (c == 'e') {
                if (counts[1] == 0) numUniqueVowelTypes++;
                counts[1]++;
            } else if (c == 'i') {
                if (counts[2] == 0) numUniqueVowelTypes++;
                counts[2]++;
            } else if (c == 'o') {
                if (counts[3] == 0) numUniqueVowelTypes++;
                counts[3]++;
            } else if (c == 'u') {
                if (counts[4] == 0) numUniqueVowelTypes++;
                counts[4]++;
            }
        }

        // Step 2: Handle No Vowels Case
        if (numUniqueVowelTypes == 0) {
            return 0;
        }

        // Step 3: Calculate the Product of Counts
        long productOfCounts = 1;
        for (int i = 0; i < 5; i++) {
            if (counts[i] > 0) {
                productOfCounts *= counts[i];
            }
        }

        // Step 4: Calculate Factorial of Unique Vowel Types
        long factorialUniqueTypes = 1;
        for (int i = 2; i <= numUniqueVowelTypes; i++) {
            factorialUniqueTypes *= i;
        }

        // Step 5: Return the total number of distinct strings
        // Cast to int as per problem statement, checking for potential overflow.
        // Max value is 100 (for 100 'a's) * 120 (for 5!) = 12000, which fits in int.
        return (int) (productOfCounts * factorialUniqueTypes);
    }
}