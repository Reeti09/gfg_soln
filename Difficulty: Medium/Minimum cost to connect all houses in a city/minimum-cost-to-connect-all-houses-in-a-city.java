//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[][] edge = new int[n][2];
            for (int i = 0; i < n; i++) {
                edge[i][0] = sc.nextInt();
                edge[i][1] = sc.nextInt();
            }
            Solution obj = new Solution();
            int res = obj.minCost(edge);

            System.out.println(res);
            System.out.println("~");
        }
    }
}

// } Driver Code Ends

class Solution {
    public int minCost(int[][] points) {
        int n = points.length;
        if (n == 0) return 0;

        // Priority Queue to select minimum cost edge (Prim's Algorithm)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        boolean[] visited = new boolean[n]; // To track connected houses
        pq.offer(new int[]{0, 0}); // Start with any node (0) with cost 0

        int totalCost = 0, count = 0;
        
        while (!pq.isEmpty() && count < n) {
            int[] edge = pq.poll();
            int cost = edge[0];
            int node = edge[1];

            if (visited[node]) continue;
            visited[node] = true;
            totalCost += cost;
            count++;

            // Add all valid edges to the priority queue
            for (int next = 0; next < n; next++) {
                if (!visited[next]) {
                    int nextCost = Math.abs(points[node][0] - points[next][0]) + 
                                   Math.abs(points[node][1] - points[next][1]);
                    pq.offer(new int[]{nextCost, next});
                }
            }
        }

        return totalCost;
    }
}

