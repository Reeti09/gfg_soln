// User function Template for Java

class Solution {

    static ArrayList<Integer> removeDuplicate(int arr[]) {
        // code here
        ArrayList<Integer> result=new ArrayList<>();
        HashSet<Integer> mp=new HashSet<>();
        for(int i=0;i<arr.length;i++){
            if(!mp.contains(arr[i])){
                result.add(arr[i]);
                mp.add(arr[i]);
            }
        }
        return result;
    }
}