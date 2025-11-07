// User function Template for Java

class Solution {
    public int firstNonRepeating(int[] arr) {
        // Complete the function
        Map<Integer, Integer> mp=new HashMap<>();
        for(int num: arr){
            mp.put(num, mp.getOrDefault(num,0)+1);
        }
        for(int num: arr){
            if(mp.get(num)==1) return num;
        }
        return 0;
    }
}
