//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            String s = in.readLine();

            Solution ob = new Solution();
            out.println(ob.decodeString(s));

            out.println("~");
        }
        out.close();
    }
}
// } Driver Code Ends




class Solution {
    static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int num = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // Construct the number (handles multi-digit numbers)
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                // Push the number and current string onto stacks
                countStack.push(num);
                stringStack.push(currentString);
                // Reset for new substring
                num = 0;
                currentString = new StringBuilder();
            } else if (c == ']') {
                // Decode: Pop last string and repeat `currentString` `count` times
                int count = countStack.pop();
                StringBuilder decodedString = stringStack.pop();
                while (count-- > 0) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                // Regular character, just append to current string
                currentString.append(c);
            }
        }
        
        return currentString.toString();
    }
}
