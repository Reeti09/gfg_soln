class Solution {
    public ArrayList<Integer> minAnd2ndMin(int[] arr) {
        // code here
        int min=Integer.MAX_VALUE;
        int second=Integer.MAX_VALUE;
        for(int num:arr){
            if(num<min){
                second=min;
                min=num;
            }
            else if(num<second && num!=min){
                second=num;
            }
            
        }
        ArrayList<Integer> result=new ArrayList<>();
        if(second==Integer.MAX_VALUE){
            result.add(-1);
        }
        else{
            result.add(min);
            result.add(second);
        }
        return result;
    }
}
