// User function Template for Java

class Solution {
    public long sum_of_ap(long n, long a, long d) {
        // Code here
        long term1 = 2 * a;
        long term2 = (n - 1) * d;
        
        long sum = n * (term1 + term2) / 2;
        
        return sum;
        
    }
}