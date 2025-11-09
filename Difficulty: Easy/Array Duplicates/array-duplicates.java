class Solution {
    public ArrayList<Integer> findDuplicates(int[] arr) {
        // code here
        ArrayList<Integer> dup=new ArrayList<>();
        int n=arr.length;
        for(int i=0;i<n;i++){
            int val=Math.abs(arr[i]);
            int index=val-1;
            if(arr[index]<0){
                dup.add(val);
                
            }
            else{
                arr[index]=-arr[index];
            }
        }
        Collections.sort(dup);
        return dup;
        
        
    }
}