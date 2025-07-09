import java.util.*;
class Solution {
    public boolean isCycle(int V, int[][] edges) {
        // Code here  
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            int u=edge[0];
            int v=edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean[] visited=new boolean[V];
        int[] parent=new int[V];
        Arrays.fill(parent,-1);
        for(int i=0;i<V;i++){
            if(!visited[i]){
                if(detectBfs(i, adj, visited, parent)){
                    return true;
                }
                
            }
            
        }
        return false;
        
    }
    private boolean detectBfs(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int[] parent){
        Queue<Integer> q=new LinkedList<>();
        q.offer(start);
        visited[start]=true;
        parent[start]=-1;
        while(!q.isEmpty()){
            int u=q.poll();
            for(int v: adj.get(u)){
                if(!visited[v]){
                    visited[v]=true;
                    parent[v]=u;
                    q.offer(v);
                }
                else if(v!=parent[u]){
                return true;
                }
            }
            
              
            
        }
        return false;
        
    }
    
}