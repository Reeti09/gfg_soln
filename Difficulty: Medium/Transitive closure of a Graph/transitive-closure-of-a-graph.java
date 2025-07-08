// User function Template for Java

class Solution {
    static ArrayList<ArrayList<Integer>> transitiveClosure(int n, int graph[][]) {
        // code here
        ArrayList<ArrayList<Integer>> tc=new ArrayList<>();
        for(int i=0;i<n;i++){
            ArrayList<Integer> row=new ArrayList<>();
            for(int j=0;j<n;j++){
                row.add(graph[i][j]);
            }
            tc.add(row);
        }
        for(int i=0;i<n;i++){
            tc.get(i).set(i,1);
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(tc.get(i).get(k)==1 && tc.get(k).get(j)==1){
                        tc.get(i).set(j,1);
                    }
                }
            }
        }
        return tc;
        
    }
}