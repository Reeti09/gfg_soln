import java.util.Arrays;

class Solution {
    public void mergeArrays(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        int i = n - 1; // Pointer for array a (from the end)
        int j = 0;     // Pointer for array b (from the beginning)

        while (i >= 0 && j < m) {
            if (a[i] > b[j]) {
                // Swap elements
                int temp = a[i];
                a[i] = b[j];
                b[j] = temp;
                i--;
                j++;
            } else {
                // If a[i] <= b[j], no need to swap.
                // The arrays are already partitioned correctly.
                break;
            }
        }
        
        // Sort a and b individually to get the final sorted result.
        // This is the missing step that causes the incorrect output.
        Arrays.sort(a);
        Arrays.sort(b);
    }
}