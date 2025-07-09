import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int countIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        // --- THE CORRECTED 8-DIRECTIONAL ARRAYS ---
        // These cover all 8 neighbors:
        // Top-Left, Up, Top-Right, Left, Right, Bottom-Left, Down, Bottom-Right
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        // ------------------------------------------

        for(int r = 0; r < n; r++){
            for(int c = 0; c < m; c++){
                if(grid[r][c] == 'L'){
                    count++; // Found a new island
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{r, c});
                    grid[r][c] = 'W'; // Mark as visited (sunk)

                    while(!q.isEmpty()){
                        int[] current = q.poll();
                        int curR = current[0];
                        int curC = current[1];

                        // Iterate through all 8 directions
                        for(int i = 0; i < 8; i++){ // Loop count must be 8 for 8 directions
                            int newR = dr[i] + curR;
                            int newC = dc[i] + curC;

                            // Check bounds and if it's an unvisited land cell
                            if(newR >= 0 && newR < n && newC >= 0 && newC < m && grid[newR][newC] == 'L'){
                                grid[newR][newC] = 'W'; // Mark as visited
                                q.offer(new int[]{newR, newC});
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}