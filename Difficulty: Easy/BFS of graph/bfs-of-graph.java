class Solution {
    // Function to return Breadth First Search Traversal of given graph.
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        ArrayList<Integer> bfsTraversal=new ArrayList<>();
        Queue<Integer> q=new LinkedList<>();
        int v=adj.size();
        boolean[] visited=new boolean[v];
        q.add(0);
        visited[0]=true;
        while(!q.isEmpty()){
            int current=q.poll();
            bfsTraversal.add(current);
            for(int neighbor: adj.get(current)){
                if(!visited[neighbor]){
                    visited[neighbor]=true;
                    q.add(neighbor);
                }
            }
            
        }
        return bfsTraversal;
        
    }
}