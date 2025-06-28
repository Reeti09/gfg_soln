import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    /**
     * Counts the number of elements in a sorted array 'arr' that are less than or equal to 'val'.
     * This uses a binary search variant to efficiently find the count.
     *
     * @param arr The sorted array.
     * @param val The target value.
     * @return The count of elements in 'arr' that are <= 'val'.
     */
    private static int countLessThanOrEqual(int[] arr, int val) {
        int low = 0;
        int high = arr.length - 1;
        int count = 0; // This variable will store the count of elements <= val

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= val) {
                // If arr[mid] is less than or equal to 'val', it means
                // all elements from arr[0] to arr[mid] (inclusive) are also <= 'val'.
                // So, 'mid + 1' is a potential count.
                // We then try to find if there are even more elements to the right
                // that also satisfy the condition.
                count = mid + 1; // Update count to include elements up to mid
                low = mid + 1;   // Move to the right half to find more elements <= val
            } else {
                // If arr[mid] is greater than 'val', then 'val' (or elements <= 'val')
                // must be in the left half of the current search space.
                high = mid - 1; // Move to the left half
            }
        }
        return count; // 'count' will hold the total number of elements <= 'val'
    }

    /**
     * For each element in array 'a', counts how many elements in array 'b'
     * are less than or equal to it.
     *
     * @param a The first unsorted array.
     * @param b The second unsorted array.
     * @return An ArrayList containing the counts for each element of 'a'.
     */
    public static ArrayList<Integer> countLessEq(int a[], int b[]) {
        // Step 1: Sort array b to enable efficient binary search.
        // This is crucial for optimizing the counting process.
        Arrays.sort(b);

        // Step 2: Initialize an ArrayList to store the results.
        ArrayList<Integer> result = new ArrayList<>();

        // Step 3: Iterate through each element of array 'a'.
        // For each element, use the 'countLessThanOrEqual' helper function
        // to find the number of elements in the sorted 'b' array that are less than or equal to it.
        for (int i = 0; i < a.length; i++) {
            int count = countLessThanOrEqual(b, a[i]);
            result.add(count); // Add the calculated count to the result list
        }

        return result; // Return the ArrayList of counts
    }
}