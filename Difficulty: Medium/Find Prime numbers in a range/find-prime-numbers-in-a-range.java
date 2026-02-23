import java.util.ArrayList; // Don't forget this!

class Solution {
    ArrayList<Integer> primeRange(int M, int N) {
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = M; i <= N; i++) {
            if (isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }

    // Fixed: Added 'int' before 'n'
    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}