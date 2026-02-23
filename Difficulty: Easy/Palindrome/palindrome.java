class Solution {
    public boolean isPalindrome(int n) {
        // code here
        if(n<0) return false;
        int original=n;
        int rev=0;
        while(n>0){
            int lastDigit=n%10;
            rev=(rev*10)+lastDigit;
            n=n/10;
        }
        return original==(int)rev;
        
    }
}