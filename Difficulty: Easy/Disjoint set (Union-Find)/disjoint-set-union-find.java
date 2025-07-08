/*Complete the function below*/
class GfG {
    int find(int par[], int x) {
        // add code here.
        if(par[x]==x){
            return x;
        }
        return par[x]=find(par, par[x]);
    }

    void unionSet(int par[], int x, int z) {
        // add code here.
        int rootX=find(par, x);
        int rootZ=find(par, z);
        if(rootX!=rootZ){
            par[rootX]=rootZ;
        }
    }
}