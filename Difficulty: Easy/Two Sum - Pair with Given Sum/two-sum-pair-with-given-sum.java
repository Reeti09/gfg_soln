class Solution {
    boolean twoSum(int arr[], int target) {
        // code here
        HashSet<Integer> seen=new HashSet<>();
        for(int num: arr){
            int complement=target-num;
            if(seen.contains(complement)){
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}