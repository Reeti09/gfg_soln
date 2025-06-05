import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // Memoization array: dp[i] stores the number of paths from node i to dest
    private int[] dp;
    private List<List<Integer>> adj; // Adjacency list

    /**
     * Counts all unique paths from a source to a destination in a Directed Acyclic Graph (DAG).
     *
     * @param edges The edges of the graph. Each inner array is [u, v] representing a directed edge from u to v.
     * @param V The total number of vertices in the graph (0 to V-1).
     * @param src The starting node.
     * @param dest The destination node.
     * @return The number of unique paths from src to dest. Returns 0 if no path exists.
     */
    public int countPaths(int[][] edges, int V, int src, int dest) {
        // Initialize adjacency list
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the graph using the adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
        }

        // Initialize memoization array with -1 (indicating uncomputed)
        dp = new int[V];
        Arrays.fill(dp, -1);

        // Start DFS from the source node
        return dfs(src, dest);
    }

    /**
     * Recursive DFS function to count paths from 'current' node to 'dest'.
     *
     * @param current The current node in the DFS traversal.
     * @param dest The target destination node.
     * @return The number of paths from 'current' to 'dest'.
     */
    private int dfs(int current, int dest) {
        // Base Case 1: If current node is the destination, we found one path.
        if (current == dest) {
            return 1;
        }

        // Base Case 2: If this node's path count has already been computed, return it.
        if (dp[current] != -1) {
            return dp[current];
        }

        // Recursive Step: Explore all neighbors
        int pathCount = 0;
        for (int neighbor : adj.get(current)) {
            pathCount += dfs(neighbor, dest);
        }

        // Memoize the result before returning
        dp[current] = pathCount;
        return pathCount;
    }
}