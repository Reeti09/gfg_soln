import java.util.*;

class Solution {
    public ArrayList<Integer> customSort(int[] arr) {
        int n = arr.length;
        int mid = n / 2;

        // 1. Sort ONLY the first half in ascending order
        // Index range: [0, mid)
        Arrays.sort(arr, 0, mid);

        // 2. Sort ONLY the second half in ascending order first
        // Index range: [mid, n)
        Arrays.sort(arr, mid, n);

        // 3. Reverse ONLY the second half to make it descending
        int low = mid;
        int high = n - 1;
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }

        // 4. Convert the modified array to an ArrayList
        ArrayList<Integer> result = new ArrayList<>();
        for (int num : arr) {
            result.add(num);
        }
        return result;
    }
}