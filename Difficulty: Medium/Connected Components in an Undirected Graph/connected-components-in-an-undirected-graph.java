
class Solution {
    private void bfs(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> current){
        Queue<Integer> q=new LinkedList<>();
        q.offer(start);
        visited[start]=true;
        while(!q.isEmpty()){
            int u=q.poll();
            current.add(u);
            for(int v: adj.get(u)){
                if(!visited[v]){
                    visited[v]=true;
                    q.offer(v);
                    
                }
            }
        }
    }
    public ArrayList<ArrayList<Integer>> getComponents(int v, int[][] edges) {
        // code here
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            int u= edge[0];
            int r= edge[1];
            adj.get(u).add(r);
            adj.get(r).add(u);
        }
        ArrayList<ArrayList<Integer>> allComp=new ArrayList<>();
        boolean[] visited=new boolean[v];
        for(int i=0;i<v;i++){
            if(!visited[i]){
                ArrayList<Integer> current=new ArrayList<>();
                bfs(i, adj, visited, current);
                allComp.add(current);
            }
        }
        return allComp;
        
    }
}