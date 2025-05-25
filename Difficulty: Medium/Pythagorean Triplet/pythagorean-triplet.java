

class Solution {
    boolean pythagoreanTriplet(int[] arr) {
        int n = arr.length;
        HashSet<Integer> squares = new HashSet<>();

        // Add square of each number to the set
        for (int num : arr) {
            squares.add(num * num);
        }

        // For every pair (a, b), check if a^2 + b^2 exists in the set
        for (int i = 0; i < n; i++) {
            int aSq = arr[i] * arr[i];
            for (int j = i + 1; j < n; j++) {
                int bSq = arr[j] * arr[j];
                if (squares.contains(aSq + bSq)) {
                    return true;
                }
            }
        }

        return false;
    }
}
