// User function Template for Java

class Solution {
    String equilibrium(int arr[]) {
        // code here
        int n=arr.length;
        if(n==0) return "false";
        long total=0;
        for(int x: arr){
            total+=x;
        }
        long left=0;
        for(int i=0;i<n;i++){
            long right=total-left-arr[i];
            if(left==right){
                return "true";
            }
            left+=arr[i];
        }
        
        return "false";
    }
}