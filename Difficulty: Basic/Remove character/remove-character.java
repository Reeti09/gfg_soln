// User function Template for Java
class Solution {
    static String removeChars(String str1, String str2) {
        // code here
        boolean[] charRem=new boolean[26];
        for(char c: str2.toCharArray()){
            charRem[c-'a']=true;
        }
        StringBuilder result=new StringBuilder();
        for(char c: str1.toCharArray()){
            if(!charRem[c-'a']) result.append(c);
        }
        return result.toString();
        
    }
}