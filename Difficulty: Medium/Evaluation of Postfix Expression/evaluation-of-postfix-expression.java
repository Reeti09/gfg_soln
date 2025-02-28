//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;


// } Driver Code Ends


class Solution {
    public int evaluate(String[] arr) {
        // code here
        Deque<Integer> stack= new ArrayDeque<>();
        for(String token: arr){
            if(Character.isDigit(token.charAt(0))|| token.length()>1){
                stack.push(Integer.parseInt(token));
            }
            else{
                int b=stack.pop();
                int a=stack.pop();
                switch(token){
                    case "+": stack.push(a+b); break;
                    case "-": stack.push(a-b); break;
                    case "/": stack.push(a/b); break;
                    case "*": stack.push(a*b); break;
                    
                }
            }
        }
        return stack.pop();
        
    }
}


//{ Driver Code Starts.

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int t = Integer.parseInt(line);
        while (t-- > 0) {
            line = reader.readLine();
            String[] arr = line.split(" ");
            Solution solution = new Solution();
            System.out.println(solution.evaluate(arr));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends