import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {

    /**
     * Counts the number of pairs (i, j) such that 0 <= i < j <= n-1 and gcd(arr[i], arr[j]) = 1.
     * This method uses the Inclusion-Exclusion Principle with the Mobius function.
     *
     * @param arr The input array of positive integers.
     * @return The number of coprime pairs.
     */
    int cntCoprime(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int maxVal = 0;
        for (int x : arr) {
            if (x > maxVal) {
                maxVal = x;
            }
        }

        // Step 1: Count frequencies of each number in the input array
        int[] counts = new int[maxVal + 1];
        for (int x : arr) {
            counts[x]++;
        }

        // Step 2: Precompute Mobius function (mu) and lowest prime factor (lp) using a sieve
        int[] mu = new int[maxVal + 1];
        int[] lp = new int[maxVal + 1]; // Stores the lowest prime factor for each number
        ArrayList<Integer> primes = new ArrayList<>();

        mu[1] = 1; // mu(1) is 1

        for (int i = 2; i <= maxVal; ++i) {
            if (lp[i] == 0) { // i is a prime number
                lp[i] = i;
                primes.add(i);
                mu[i] = -1; // mu(prime) is -1
            }
            for (int p : primes) {
                if (p > lp[i] || (long)i * p > maxVal) {
                    break; // Optimization: stop if p is greater than the smallest prime factor of i
                           // or if i*p exceeds maxVal
                }
                lp[i * p] = p;
                if (p == lp[i]) { // p divides i, so i*p has a squared prime factor
                    mu[i * p] = 0;
                } else { // p does not divide i
                    mu[i * p] = -mu[i];
                }
            }
        }

        // Step 3: Compute countMultiples[d] - number of elements in arr that are multiples of d
        long[] countMultiples = new long[maxVal + 1];
        for (int d = 1; d <= maxVal; ++d) {
            for (int m = d; m <= maxVal; m += d) {
                countMultiples[d] += counts[m];
            }
        }

        // Step 4: Calculate total coprime pairs using the Inclusion-Exclusion Principle
        // Sum (mu[d] * (number of pairs (a,b) where d divides gcd(a,b))) for all d
        // The number of pairs (a,b) where d divides gcd(a,b) is
        // countMultiples[d] * (countMultiples[d] - 1) / 2
        long totalCoprimePairs = 0;
        for (int d = 1; d <= maxVal; ++d) {
            if (mu[d] == 0) {
                continue; // If mu[d] is 0, this term doesn't contribute
            }
            long numMultiples = countMultiples[d];
            // Calculate pairs of distinct numbers that are multiples of d
            long pairsForD = numMultiples * (numMultiples - 1) / 2;
            totalCoprimePairs += (long)mu[d] * pairsForD;
        }

        return (int)totalCoprimePairs;
    }
}