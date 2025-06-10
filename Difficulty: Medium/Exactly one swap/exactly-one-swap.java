class Solution {
    public long countStrings(String s) {
        int n = s.length();

        // If string length is less than 2, no swaps are possible.
        // The problem constraints state 2 <= s.size(), but this is a good defensive check.
        if (n < 2) {
            return 0;
        }

        // 1. Calculate total possible swap operations.
        // This is N choose 2, which is N * (N - 1) / 2.
        // Use long to prevent integer overflow for large N (N up to 10^4).
        long totalPossibleSwaps = (long) n * (n - 1) / 2;

        // 2. Count character frequencies.
        // Using an int array of size 26 for lowercase English letters (a-z).
        int[] charCounts = new int[26];
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }

        // 3. Count swaps that result in the original string 's'.
        // These are swaps between two identical characters.
        // For each character that appears 'k' times, there are k * (k - 1) / 2 ways
        // to pick two positions that contain this character, resulting in the original string.
        long swapsToOriginal = 0;
        for (int count : charCounts) {
            if (count > 1) { // Only count if the character appears more than once
                swapsToOriginal += (long) count * (count - 1) / 2;
            }
        }

        // 4. Calculate the number of distinct strings.
        // Start with the number of swaps that produce a string different from the original.
        long distinctStringsCount = totalPossibleSwaps - swapsToOriginal;

        // If there was at least one swap that resulted in the original string,
        // it means the original string 's' itself is one of the distinct strings
        // that can be "obtained by exactly one swap".
        // In this case, we add 1 to include 's' in the count.
        if (swapsToOriginal > 0) {
            distinctStringsCount++;
        }
        
        return distinctStringsCount;
    }
}