import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * Finds the minimum number of pieces a binary string can be split into,
     * where each piece represents a power of 5 in decimal and has no leading zeros.
     *
     * @param s The binary string consisting only of '0' and '1'.
     * @return The minimum number of pieces, or -1 if it's not possible to split the string.
     */
    public int cuts(String s) {
        int n = s.length();

        // 1. Precompute powers of 5 as binary strings.
        // We only need powers of 5 whose binary representation length is at most 'n'.
        // Long is sufficient for powers of 5 whose binary representation fits within 63 bits.
        // For 'n' values up to around 60 (a typical maximum for N^3 problems in contests),
        // 'long' is generally sufficient for `currentPower`.
        Set<String> powersOf5Binary = new HashSet<>();
        long currentPower = 1; // Represents 5^0

        while (true) {
            String binaryRepr = Long.toBinaryString(currentPower);
            
            // If the binary representation is longer than the input string 's',
            // then no substring of 's' can represent this or larger powers of 5.
            // So, we can stop precomputing.
            if (binaryRepr.length() > n) {
                break;
            }
            
            powersOf5Binary.add(binaryRepr);
            
            // Prepare for the next power of 5.
            // Check for potential overflow before multiplication to ensure `currentPower` stays within `long` limits.
            // If `currentPower` is already close to `Long.MAX_VALUE / 5`, multiplying by 5 will overflow.
            // In such a case, further powers would also exceed `long` and likely `n` bits if `n` is not very large.
            if (Long.MAX_VALUE / 5 < currentPower) {
                break; 
            }
            currentPower *= 5;
        }
        
        // 2. Dynamic Programming Transition
        // dp[i] will store the minimum number of pieces to split the prefix s[0...i-1].
        // Initialize with a large value (representing infinity) for unreachable states.
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE; // Represents infinity
        }
        dp[0] = 0; // An empty prefix requires 0 splits.

        // Iterate through the string to fill the dp table
        for (int i = 1; i <= n; i++) {
            // For each ending position 'i', try all possible starting positions 'j'
            // for the last substring s[j...i-1].
            for (int j = 0; j < i; j++) {
                String substring = s.substring(j, i);

                // Constraint 1: No substring should have leading zeros.
                // A valid power of 5 is always positive, so its binary representation
                // must start with '1' (e.g., "1", "101", "11001"). "0" itself is not a power of 5.
                if (substring.charAt(0) == '0') {
                    continue; 
                }

                // Constraint 2: Each substring represents a power of 5 in decimal.
                if (powersOf5Binary.contains(substring)) {
                    // If the prefix s[0...j-1] was reachable (dp[j] is not infinity),
                    // then s[0...i-1] is also reachable by adding one more split.
                    if (dp[j] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        
        // 3. Result
        // If dp[n] is still Integer.MAX_VALUE, it means the entire string cannot be split
        // according to the rules.
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }
}