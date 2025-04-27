//{ Driver Code Starts
// Initial Template for Java

import java.math.*;
import java.util.*;

class Multiply {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String a = sc.next();
            String b = sc.next();
            Solution g = new Solution();
            System.out.println(g.multiplyStrings(a, b));

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    public String multiplyStrings(String s1, String s2) {
        // Remove leading zeros
        s1 = removeLeadingZeros(s1);
        s2 = removeLeadingZeros(s2);
        
        // If either is "0", result is "0"
        if (s1.equals("0") || s2.equals("0")) return "0";
        
        // Check for negativity
        boolean isNegative = false;
        if (s1.charAt(0) == '-') {
            isNegative = !isNegative;
            s1 = s1.substring(1);
        }
        if (s2.charAt(0) == '-') {
            isNegative = !isNegative;
            s2 = s2.substring(1);
        }
        
        int m = s1.length(), n = s2.length();
        int[] result = new int[m + n];
        
        // Perform multiplication like elementary school
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (s1.charAt(i) - '0') * (s2.charAt(j) - '0');
                int sum = mul + result[i + j + 1];
                
                result[i + j] += sum / 10;
                result[i + j + 1] = sum % 10;
            }
        }
        
        // Build final answer string
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (sb.length() == 0 && num == 0) continue; // skip leading zeros
            sb.append(num);
        }
        
        if (isNegative) {
            sb.insert(0, '-');
        }
        
        return sb.toString();
    }
    
    private String removeLeadingZeros(String str) {
        int i = 0;
        while (i < str.length() && (str.charAt(i) == '0' || str.charAt(i) == '+')) {
            i++;
        }
        if (i == str.length()) return "0";
        return str.substring(i);
    }
}
