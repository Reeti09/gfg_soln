class Solution {
    public List<Integer> frequencyCount(int[] arr) {
        // code here
        int n=arr.length;
        Map<Integer, Integer> mp=new HashMap<>();
        for(int e: arr){
            if(e>=1 && e<=n)
            {mp.put(e,mp.getOrDefault(e,0)+1);}
        }
        List<Integer> result = new ArrayList<>(n);
        for(int i=1;i<=n;i++){
            int freq=mp.getOrDefault(i,0);
            result.add(freq);
        }
        return result;
    }
}
