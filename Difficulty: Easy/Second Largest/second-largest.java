class Solution {
    public int getSecondLargest(int[] arr) {
        // code here
        int n=arr.length;
        if(n<2) return -1;
        int largest=Integer.MIN_VALUE;
        for(int e: arr){
            if(e>largest){
                largest=e;
            }
        }
        int secondL=-1;
        for(int e: arr){
            if(e<largest){
                if(e>secondL){
                    secondL=e;
                }
            }
        }
        return secondL;
    }
}