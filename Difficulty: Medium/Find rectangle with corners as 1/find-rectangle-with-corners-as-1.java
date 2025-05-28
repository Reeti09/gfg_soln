class Solution {
    public boolean ValidCorner(int mat[][]) {
        int n = mat.length;
        if (n == 0) { // Handle empty matrix case
            return false;
        }
        int m = mat[0].length;
        if (m == 0) { // Handle empty row case
            return false;
        }

        // Iterate through all possible first columns (c1)
        // We go up to m - 1 because c2 will be c1 + 1, so c1 must be at least one less than the last column.
        for (int c1 = 0; c1 < m; c1++) {
            // Iterate through all possible second columns (c2)
            // c2 must be greater than c1 to form a distinct pair of columns.
            for (int c2 = c1 + 1; c2 < m; c2++) {
                // 'lastRowFound' stores the index of the first row where we encountered '1's in both c1 and c2.
                // Initialize it to -1 to indicate no such row has been found yet.
                int lastRowFound = -1;

                // Iterate through each row (r) of the matrix
                for (int r = 0; r < n; r++) {
                    // Check if both cells in the current row 'r' and columns c1, c2 are '1'.
                    if (mat[r][c1] == 1 && mat[r][c2] == 1) {
                        // If 'lastRowFound' is not -1, it means we have already found a previous row
                        // (let's call it r_old = lastRowFound) where mat[r_old][c1] and mat[r_old][c2] were '1's.
                        if (lastRowFound != -1) {
                            // Since we've found another row 'r' with '1's in the same columns (c1, c2),
                            // we now have all four corners of a rectangle:
                            // (lastRowFound, c1), (lastRowFound, c2), (r, c1), (r, c2)
                            return true; // A valid rectangle is found!
                        } else {
                            // If 'lastRowFound' is -1, this is the first time we've seen '1's in both c1 and c2
                            // for this specific pair of columns. Store this row's index.
                            lastRowFound = r;
                        }
                    }
                }
            }
        }

        // If the loops complete without finding any valid rectangle, return false.
        return false;
    }
}