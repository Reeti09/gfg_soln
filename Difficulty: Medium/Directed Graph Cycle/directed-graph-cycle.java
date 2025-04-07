//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int[][] edges = new int[E][2];
            for (int i = 0; i < E; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }

            Solution obj = new Solution();
            boolean ans = obj.isCyclic(V, edges);
            System.out.println(ans ? "true" : "false");
        }
        sc.close();
    }
}
// } Driver Code Ends



class Solution {
    public boolean isCyclic(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[V];

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the graph
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++; // Increment in-degree
        }

        // Kahn's Algorithm (BFS-based Topological Sorting)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0; // Count of processed nodes
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;

            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If count of processed nodes != total nodes, there's a cycle
        return count != V;
    }
}
