//{ Driver Code Starts
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = Integer.parseInt(sc.nextLine());

        while (t-- > 0) {
            int V = Integer.parseInt(sc.nextLine());
            int E = Integer.parseInt(sc.nextLine());

            List<int[]> edgeList = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                String[] parts = sc.nextLine().split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int w = Integer.parseInt(parts[2]);
                edgeList.add(new int[] {u, v, w});
                edgeList.add(new int[] {v, u, w});
            }

            int[][] edges = new int[edgeList.size()][3];
            for (int i = 0; i < edgeList.size(); i++) {
                edges[i] = edgeList.get(i);
            }

            int src = Integer.parseInt(sc.nextLine());

            Solution obj = new Solution();
            int[] res = obj.dijkstra(V, edges, src);

            for (int val : res) {
                System.out.print(val + " ");
            }
            System.out.println();
            System.out.println("~");
        }

        sc.close();
    }
}

// } Driver Code Ends


// User function Template for Java


class Solution {
    static class Node {
        int vertex, weight;
        Node(int v, int w) {
            vertex = v;
            weight = w;
        }
    }

    public int[] dijkstra(int V, int[][] edges, int src) {
        // Step 1: Convert edges array into an adjacency list
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj.get(u).add(new Node(v, w));
            adj.get(v).add(new Node(u, w)); // If the graph is undirected
        }

        // Step 2: Initialize distance array with Infinity (MAX_VALUE)
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 3: Use a Min Heap (PriorityQueue) to store (distance, vertex)
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        pq.add(new Node(src, 0));

        // Step 4: Process the graph
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex, weight = neighbor.weight;

                // If a shorter path is found
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        return dist;
    }

    
}
