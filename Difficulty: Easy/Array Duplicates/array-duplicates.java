class Solution {
    public ArrayList<Integer> findDuplicates(int[] arr) {
        // code here
        ArrayList<Integer> result=new ArrayList<>();
        Map<Integer, Integer> mp=new HashMap<>();
        for(int num: arr){
            mp.put(num, mp.getOrDefault(num,0)+1);
            if(mp.get(num)==2){
                result.add(num);
            }
        }
        return result;
    }
}