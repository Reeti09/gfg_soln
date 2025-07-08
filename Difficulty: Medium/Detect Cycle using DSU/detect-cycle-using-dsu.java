import java.util.ArrayList;

class Solution {

    // --- DisjointSet Class (Helper) ---
    // This static nested class implements the core logic of the Disjoint Set Union data structure.
    // It manages the parent array for elements and provides optimized find and union operations.
    static class DisjointSet {
        int[] parent; // The parent array: parent[i] stores the immediate parent of element i.

        // Constructor: Initializes the DSU structure for 'n' elements (nodes).
        // Initially, each element is in its own set, meaning it's its own parent.
        public DisjointSet(int n) {
            parent = new int[n]; // Array to store parent for nodes 0 to n-1.
            for (int i = 0; i < n; i++) {
                parent[i] = i; // Every node is initially the root of its own set.
            }
        }

        /**
         * Finds the ultimate parent (representative) of the set containing element 'i'.
         * This method incorporates Path Compression, a critical optimization.
         * Path compression flattens the tree by making all nodes on the path
         * from 'i' to the root directly point to the root. This significantly
         * reduces the time complexity for subsequent 'find' operations.
         *
         * @param i The element for which to find the representative.
         * @return The representative (root) of the set containing 'i'.
         */
        public int find(int i) {
            // Base case: If 'i' is its own parent, it means 'i' is the root of its set.
            if (parent[i] == i) {
                return i;
            }
            // Recursive step with Path Compression:
            // Recursively find the ultimate root of the current parent[i].
            // Then, update parent[i] to point directly to this ultimate root.
            // This flattens the path as the recursion unwinds.
            return parent[i] = find(parent[i]);
        }

        /**
         * Performs the union of the sets containing elements 'u' and 'v'.
         * A simple union strategy is employed: the root of one set is made to point
         * to the root of the other set. (For very large-scale applications,
         * Union by Rank or Union by Size optimizations are typically added for better
         * worst-case time complexity, but for constraints like V, E <= 100, this simple
         * approach combined with path compression is efficient enough).
         *
         * @param u One element belonging to a set.
         * @param v The other element belonging to a set.
         * @return true if a merge successfully occurred (meaning 'u' and 'v' were
         * originally in different sets); false if they were already in the
         * same set (this condition is crucial for cycle detection).
         */
        public boolean union(int u, int v) {
            // Find the ultimate parent (root) of 'u's set.
            int rootU = find(u);
            // Find the ultimate parent (root) of 'v's set.
            int rootV = find(v);

            // If the roots are different, it means 'u' and 'v' belong to different sets.
            // We proceed to merge these two sets.
            if (rootU != rootV) {
                // Make the root of 'u's set point to the root of 'v's set.
                // This effectively merges the component that 'u' belongs to into
                // the component that 'v' belongs to, with 'rootV' becoming the
                // new representative for the combined set.
                parent[rootU] = rootV;
                return true; // Indicates that a successful merge (union) took place.
            }
            // If rootU == rootV, it means 'u' and 'v' are already in the same set.
            // In the context of cycle detection, if we are processing an edge (u, v)
            // and find that 'u' and 'v' are already connected through an existing path,
            // adding this edge would create a cycle.
            return false; // Indicates that 'u' and 'v' were already connected.
        }
    }

    /**
     * Function to detect a cycle in an undirected graph using the Disjoint Set Union (DSU) algorithm.
     *
     * The algorithm's core idea for cycle detection in an undirected graph is:
     * 1. Initialize a DSU structure where each node is in its own set.
     * 2. Iterate through each edge (u, v) in the graph.
     * 3. For each edge, find the representatives (roots) of the sets containing u and v.
     * 4. If the roots are the same, it means u and v are already connected via an existing path,
     * and adding this edge forms a cycle. In this case, a cycle is detected.
     * 5. If the roots are different, it means u and v belong to different components,
     * and adding this edge connects them. The DSU performs a union operation to merge their sets.
     * 6. If all edges are processed and no cycle is detected, the graph is acyclic.
     *
     * @param V   The number of nodes in the graph (typically 0-indexed, from 0 to V-1).
     * @param adj An ArrayList of ArrayLists representing the adjacency list of the graph.
     * For an undirected graph, if (u, v) is an edge, then 'v' is in adj[u] and 'u' is in adj[v].
     * @return 1 if a cycle is detected in the graph, 0 otherwise.
     */
    public int detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // 1. Initialize the Disjoint Set Union (DSU) structure for 'V' nodes.
        // Each node starts as the root of its own individual set.
        DisjointSet ds = new DisjointSet(V);

        // 2. Iterate through all nodes (u) in the graph, from 0 to V-1.
        for (int u = 0; u < V; u++) {
            // 3. For each node 'u', iterate through all its neighbors 'v' in the adjacency list.
            for (int v : adj.get(u)) {
                // IMPORTANT for undirected graphs:
                // To avoid processing each unique undirected edge {u, v} twice (i.e., once as (u,v)
                // when iterating from 'u', and again as (v,u) when iterating from 'v'),
                // and thus potentially misinterpreting a simple neighbor connection as a cycle,
                // we only process an edge if 'u' is strictly less than 'v'.
                // This ensures each unique edge is considered exactly one time.
                if (u < v) {
                    // Attempt to union the sets containing 'u' and 'v'.
                    // The `union` method returns `false` if 'u' and 'v' were already found
                    // to be in the same set (i.e., their roots were identical).
                    if (!ds.union(u, v)) {
                        // If `union` returns `false`, it means that 'u' and 'v' were already connected
                        // through an existing path in the graph before considering the current edge (u, v).
                        // Adding this edge (u, v) now completes a closed loop, which signifies a cycle.
                        return 1; // Cycle detected, return 1 immediately.
                    }
                }
            }
        }

        // 4. If the entire loop completes without the `if (!ds.union(u, v))` condition
        // ever evaluating to `true`, it means no cycle was detected in the graph.
        // The graph is acyclic.
        return 0; // No cycle detected.
    }
}