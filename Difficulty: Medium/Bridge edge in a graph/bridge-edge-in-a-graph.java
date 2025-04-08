//{ Driver Code Starts
import java.util.*;


// } Driver Code Ends

class Solution {
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    public boolean isBridge(int V, int[][] edges, int c, int d) {
        // Step 1: Construct adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Step 2: Count connected components before removing the edge
        boolean[] visited = new boolean[V];
        int componentsBefore = 0;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                componentsBefore++;
                dfs(i, adj, visited);
            }
        }

        // Step 3: Remove the edge (c, d)
        adj.get(c).remove((Integer) d);
        adj.get(d).remove((Integer) c);

        // Step 4: Count components after edge removal
        Arrays.fill(visited, false);
        int componentsAfter = 0;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                componentsAfter++;
                dfs(i, adj, visited);
            }
        }

        // Step 5: If components increase, it is a bridge
        return componentsAfter > componentsBefore;
    }
}



//{ Driver Code Starts.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = Integer.parseInt(sc.nextLine().trim());
        while (t-- > 0) {
            // V and E on separate lines
            int V = Integer.parseInt(sc.nextLine().trim());
            int E = Integer.parseInt(sc.nextLine().trim());

            // Using a 2D array to store edges.
            int[][] edges = new int[E][2];
            for (int i = 0; i < E; i++) {
                // Use split("\\s+") to handle one or more whitespace characters
                String[] parts = sc.nextLine().trim().split("\\s+");
                edges[i][0] = Integer.parseInt(parts[0]);
                edges[i][1] = Integer.parseInt(parts[1]);
            }

            // c and d on separate lines
            int c = Integer.parseInt(sc.nextLine().trim());
            int d = Integer.parseInt(sc.nextLine().trim());

            Solution obj = new Solution();
            boolean result = obj.isBridge(V, edges, c, d);
            System.out.println(result ? "true" : "false");
            System.out.println("~");
        }

        sc.close();
    }
}
// } Driver Code Ends