// User function Template for Java

class Solution {
    int findMaxSum(int arr[]) {
        // code here
        int n=arr.length;
        if(n==0) return 0;
        if(n==1) return arr[0];
        int inc=arr[0];
        int exc=0;
        for(int i=1;i<n;i++){
            int oldInc=inc;
            inc=exc+arr[i];
            exc=Math.max(exc, oldInc);
        }
        return Math.max(inc, exc);
        
    }
}