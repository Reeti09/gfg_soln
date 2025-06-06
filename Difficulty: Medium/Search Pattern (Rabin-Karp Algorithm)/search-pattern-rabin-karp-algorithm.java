

class Solution {
    ArrayList<Integer> search(String pat, String txt) {
        ArrayList<Integer> result = new ArrayList<>(); // Stores the starting indices of found patterns

        int M = pat.length(); // Length of the pattern
        int N = txt.length(); // Length of the text

        // If the pattern is longer than the text, it can't be found
        if (M > N) {
            return result;
        }

        // Choose a large prime number for modulo operation
        // This helps in reducing hash collisions.
        int q = 101; // A common prime for hashing

        // Choose a base for the hash function (e.g., 256 for ASCII characters)
        // This is typically the number of characters in the alphabet.
        int d = 256;

        long patHash = 0; // Hash value for the pattern
        long txtHash = 0; // Hash value for the current window of text

        // h will be d^(M-1) % q
        // This value is used to remove the leading digit's contribution when rolling the hash
        long h = 1;
        for (int i = 0; i < M - 1; i++) {
            h = (h * d) % q;
        }

        // Calculate the hash value for the pattern and the first window of text
        for (int i = 0; i < M; i++) {
            patHash = (patHash * d + pat.charAt(i)) % q;
            txtHash = (txtHash * d + txt.charAt(i)) % q;
        }

        // Slide the pattern over the text one by one
        for (int i = 0; i <= N - M; i++) {
            // If hash values match, then check character by character
            // This is to handle spurious hits (hash collisions)
            if (patHash == txtHash) {
                boolean match = true;
                for (int j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                // If an exact match is found, add the starting index to the result list
                if (match) {
                    result.add(i + 1); // Problem statements often ask for 1-based indexing.
                                       // If 0-based indexing is required, use `result.add(i);`
                }
            }

            // Calculate hash value for the next window of text
            // from the current window's hash value
            if (i < N - M) {
                // Remove leading digit, add trailing digit, and apply modulo
                txtHash = (d * (txtHash - txt.charAt(i) * h) + txt.charAt(i + M)) % q;

                // Ensure txtHash remains positive (Java's % operator can return negative results for negative operands)
                if (txtHash < 0) {
                    txtHash = (txtHash + q);
                }
            }
        }

        return result;
    }
}