// User function Template for Java
class Solution {
    static boolean armstrongNumber(int n) {
        // code here
        int num=n;
        int rev=0;
        int c=0;
        while(n>0){
            int r=n%10;
            rev+=(r*r*r);
            n=n/10;
            
        }
        if(num==rev){
            return true;
        }
        else{
            return false;
        }
        
    }
}