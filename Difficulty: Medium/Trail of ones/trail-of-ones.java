class Solution {

    /**
     * Helper method to calculate the kth Fibonacci number.
     * Fib(0) = 0, Fib(1) = 1, Fib(2) = 1, Fib(3) = 2, ...
     * This is a private helper as it's only used internally by countConsec.
     *
     * @param k The index of the Fibonacci number to calculate.
     * @return The kth Fibonacci number.
     */
    private long calculateFibonacci(int k) {
        if (k <= 0) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        long a = 0; // Represents Fib(i-2)
        long b = 1; // Represents Fib(i-1)
        long c; // Represents Fib(i)

        for (int i = 2; i <= k; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    /**
     * Counts the number of binary strings of length n that contain
     * at least one pair of consecutive 1's.
     * Uses the complementary counting principle:
     * Total strings - Strings with no consecutive 1's.
     *
     * Constraints typically allow 'int' for n, but the result might exceed 'int' max,
     * so 'long' is used for intermediate calculations and the return type should ideally be 'long'.
     * However, since the problem signature is 'public int countConsec(int n)',
     * we will cast the final result to int. Be aware of potential overflow for very large n
     * if the platform expects 'int' return for n values leading to results > 2*10^9.
     * Assuming N is such that the answer fits in int.
     *
     * @param n The length of the binary strings.
     * @return The count of binary strings with at least one pair of consecutive 1's.
     */
    public int countConsec(int n) {
        // Handle edge case for n = 0 if necessary, though problem usually implies n >= 1
        if (n == 0) {
            return 0; // No strings of length 0 have consecutive ones
        }
        
        // Total number of binary strings of length n is 2^n
        // Using long to ensure calculation does not overflow before subtraction
        long totalStrings = 1L << n; // Equivalent to Math.pow(2, n)

        // Number of binary strings of length n that do NOT contain consecutive 1's
        // This is the (n+2)-th Fibonacci number.
        // Fib(0)=0, Fib(1)=1, Fib(2)=1, Fib(3)=2, Fib(4)=3, Fib(5)=5...
        long stringsWithoutConsecutiveOnes = calculateFibonacci(n + 2);

        // The result is total strings minus strings without consecutive ones.
        // Cast to int as per the method signature.
        return (int)(totalStrings - stringsWithoutConsecutiveOnes);
    }
}