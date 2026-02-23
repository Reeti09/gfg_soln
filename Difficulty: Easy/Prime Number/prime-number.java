class Solution {
    static boolean isPrime(int n) {
        // code here
        
        int c=0;
        for(int i=1;i<=n;i++){
            if(n%i==0){
                c++;
                
            }
            
        }
        if(c==2){
            return true;
        }
        else{
            return false;
        }
    }
}