class Solution {
    public static int sumSubstrings(String s) {
        int n = s.length();
        long result = 0;
        long prev = 0;
        int mod = 1000000007; // optional, if you want to avoid overflow

        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            prev = (prev * 10 + (long) num * (i + 1));
            result += prev;
        }

        return (int) result; // you can also use: return (int)(result % mod);
    }

    
}
