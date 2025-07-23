class Solution {
    public int subarraySum(int[] arr) {
        long totalSum = 0; // Use long to prevent overflow during intermediate calculations,
                           // even if the final sum fits in int.
        int N = arr.length;

        for (int i = 0; i < N; i++) {
            // Contribution of arr[i] = arr[i] * (number of subarrays arr[i] is part of)
            // Number of subarrays = (i + 1) * (N - i)
            // (i + 1) is the number of possible start positions to the left of or at arr[i]
            // (N - i) is the number of possible end positions to the right of or at arr[i]
            
            long contribution = (long)arr[i] * (i + 1) * (N - i);
            totalSum += contribution;
        }

        return (int)totalSum; // Cast back to int as per problem guarantee
    }
}