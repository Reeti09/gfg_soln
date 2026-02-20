class Solution {
    public void reverseArray(int arr[]) {
        // code here
        int n=arr.length;
        int[] result=new int[n];
        for(int i=0;i<n;i++){
            result[i]=arr[n-i-1];
        }
        for(int i = 0; i < n; i++) {
            arr[i] = result[i];
        }
    }
}