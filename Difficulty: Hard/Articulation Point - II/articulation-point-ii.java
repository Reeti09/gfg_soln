//{ Driver Code Starts
import java.util.*;

public class Main {
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
            ArrayList<Integer> ans = obj.articulationPoints(V, edges);
            Collections.sort(ans);
            for (int val : ans) {
                System.out.print(val + " ");
            }
            System.out.println();
            System.out.println("~");
        }
    }
}
// } Driver Code Ends



class Solution {
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        
        // Convert edges into an adjacency list
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]); // Undirected graph
        }
        
        int[] tin = new int[V]; // Discovery time
        int[] low = new int[V]; // Lowest reachable node
        boolean[] visited = new boolean[V];
        boolean[] isArticulation = new boolean[V];
        
        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);
        
        int timer = 0;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, -1, timer, visited, tin, low, adj, isArticulation);
            }
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isArticulation[i]) {
                result.add(i);
            }
        }
        
        return result.isEmpty() ? new ArrayList<>(Arrays.asList(-1)) : result;
    }
    
    private static void dfs(int node, int parent, int timer, boolean[] visited, int[] tin, int[] low, 
                            List<List<Integer>> adj, boolean[] isArticulation) {
        visited[node] = true;
        tin[node] = low[node] = timer++;
        
        int children = 0;
        
        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue; // Ignore parent node
            
            if (!visited[neighbor]) {
                dfs(neighbor, node, timer, visited, tin, low, adj, isArticulation);
                low[node] = Math.min(low[node], low[neighbor]);
                
                // Condition to check articulation point
                if (low[neighbor] >= tin[node] && parent != -1) {
                    isArticulation[node] = true;
                }
                children++;
            } else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }
        
        // Special case: If the node is the root and has more than 1 child
        if (parent == -1 && children > 1) {
            isArticulation[node] = true;
        }
    }
}
