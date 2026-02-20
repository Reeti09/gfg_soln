class Solution {
    public int getSecondLargest(int[] arr) {
        // code here
        int max=-1;
        int second=-1;
        for(int num: arr){
            
            if(num>max){
                second=max;
                max=num;
            }
            else if(num>second && num!=max){
                second=num;
            }
        }
        return second;
    }
}