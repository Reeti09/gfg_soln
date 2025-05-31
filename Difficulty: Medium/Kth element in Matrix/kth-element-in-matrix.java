import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {

    // Helper class to store the element's value, its row index, and its column index.
    // Making it static nested class allows it to be used without an instance of Solution.
    static class Cell {
        int value;
        int row;
        int col;

        public Cell(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Finds the Kth smallest element in a row-wise and column-wise sorted matrix.
     *
     * @param matrix The input N x N matrix.
     * @param k      The Kth smallest element to find (1-indexed).
     * @return The Kth smallest element.
     */
    public int kthSmallest(int[][] matrix, int k) {
        // Get the dimension of the square matrix.
        // If the matrix is empty (matrix.length is 0), N will be 0.
        int N = matrix.length;

        // Handle edge case: empty matrix
        if (N == 0) {
            // According to problem constraints, matrix will usually not be empty,
            // but it's good practice. Returning -1 or throwing an exception could be options.
            return -1; // Or throw new IllegalArgumentException("Matrix is empty");
        }

        // Create a min-heap.
        // It will store 'Cell' objects and prioritize them based on their 'value' field.
        // The initial capacity (N) is a hint for optimization.
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(N, Comparator.comparingInt(c -> c.value));

        // Step 1: Add the first element of each row to the min-heap.
        // These are the smallest available elements from each row initially.
        for (int i = 0; i < N; i++) {
            // Add (value, row_index, column_index). Initially, column_index is 0 for all rows.
            minHeap.offer(new Cell(matrix[i][0], i, 0));
        }

        int result = -1; // Variable to store the Kth smallest element

        // Step 2: Extract elements 'k' times.
        // We iterate 'k' times because we are looking for the k-th smallest element.
        for (int count = 0; count < k; count++) {
            // Check if the heap becomes empty before we extract 'k' elements.
            // This would mean 'k' is larger than the total number of elements in the matrix.
            // Based on typical problem constraints (k <= N*N), this check is usually defensive.
            if (minHeap.isEmpty()) {
                // If this happens, it indicates an invalid 'k' for the given matrix.
                return -1; // Or throw new IndexOutOfBoundsException("k is greater than total elements.");
            }

            // Extract the smallest element from the heap.
            // This is the (count+1)-th smallest element found so far in the matrix.
            Cell current = minHeap.poll();

            result = current.value; // Store its value

            // Step 3: If the extracted element has a next element in its row, add that next element to the heap.
            // This maintains the property that the heap always contains the smallest unvisited element
            // from each active row.
            // Check if there's a next column in the same row (current.col + 1 < N).
            if (current.col + 1 < N) {
                minHeap.offer(new Cell(matrix[current.row][current.col + 1], current.row, current.col + 1));
            }
        }

        // After 'k' extractions, 'result' will hold the value of the k-th smallest element.
        return result;
    }
}