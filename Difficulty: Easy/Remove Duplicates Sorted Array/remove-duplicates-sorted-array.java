class Solution {
    // Function to remove duplicates from the given array.
    ArrayList<Integer> removeDuplicates(int[] arr) {
        // code here
        ArrayList<Integer> result=new ArrayList<>();
        int n=arr.length;
        if(n==0){
            return result;
        }
        int i=0;
        for(int j=1;j<n;j++){
            if(arr[i]!=arr[j]){
                i++;
                arr[i]=arr[j];
            }
        }
        for (int k = 0; k <= i; k++) {
            result.add(arr[k]);
        }
        
        return result;
        
    }
}
