class Solution {
    public ArrayList<ArrayList<Integer>> countFreq(int[] arr) {
        // code here
        Map<Integer, Integer> mp=new LinkedHashMap<>();
        for(int num: arr){
            mp.put(num, mp.getOrDefault(num,0)+1);
            
        }
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry: mp.entrySet()){
            ArrayList<Integer> pair=new ArrayList<>();
            pair.add(entry.getKey());
            pair.add(entry.getValue());
            result.add(pair);
        }
        return result;
    }
}