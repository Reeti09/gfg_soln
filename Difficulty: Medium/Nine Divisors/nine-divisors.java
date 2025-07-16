import java.util.ArrayList;
import java.util.List;

class Solution {
    public static int countNumbers(int n) {
        int count = 0;

        // Precompute primes up to sqrt(n)
        // Max value of n is 10^5, so sqrt(n) is around 316
        // Max value of n is 10^18, so sqrt(n) is around 10^9
        // This problem has n up to 10^5 according to typical competitive programming constraints for "Medium" problems
        // Let's assume n <= 10^5 for now, if it's larger, we'd need a different approach for prime generation.
        // For n up to 10^5, max prime we need is sqrt(10^5) approx 316.
        // For n up to 10^18 (if that's the actual constraint), it's more complex.
        // Let's assume n <= 10^5 for the given constraints.
        
        int limit = (int) Math.sqrt(n);
        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 0; i <= limit; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = false;
        isPrime[1] = false;
        for (int p = 2; p * p <= limit; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= limit; i += p)
                    isPrime[i] = false;
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int p = 2; p <= limit; p++) {
            if (isPrime[p]) {
                primes.add(p);
            }
        }

        // Case 1: Numbers of the form p^8
        // p^8 <= n
        // Iterate through primes
        for (int p : primes) {
            long val = (long) Math.pow(p, 8);
            if (val <= n) {
                count++;
            } else {
                // Since primes are increasing, p^8 will only get larger
                break;
            }
        }

        // Case 2: Numbers of the form p1^2 * p2^2 = (p1 * p2)^2
        // (p1 * p2)^2 <= n
        // p1 * p2 <= sqrt(n)
        for (int i = 0; i < primes.size(); i++) {
            long p1 = primes.get(i);
            // We need p1 * p2 <= sqrt(n)
            // p2 <= sqrt(n) / p1
            long p2_limit = (long) Math.sqrt(n) / p1;
            
            for (int j = i + 1; j < primes.size(); j++) {
                long p2 = primes.get(j);
                if (p2 <= p2_limit) {
                    count++;
                } else {
                    // Since p2 is increasing, no need to check further for this p1
                    break;
                }
            }
        }
        return count;
    }
}