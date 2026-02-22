// User function Template for Java

class Solution {
    static int[] replaceWithRank(int arr[], int N) {
        // code here
        int[] temp=arr.clone();
        Arrays.sort(temp);
        Map<Integer, Integer> mp=new HashMap<>();
        int rank=1;
        for(int val: temp){
            if(!mp.containsKey(val)){
                mp.put(val, rank++);
            }
        }
        int[] result=new int[N];
        for(int i=0;i<N;i++){
            result[i]=mp.get(arr[i]);
        }
        return result;
        
    }
}
