//{ Driver Code Starts
import java.util.*;

class Get_Min_From_Stack {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // Number of test cases

        while (T-- > 0) {
            int q = sc.nextInt(); // Number of queries
            Solution g = new Solution();

            while (q-- > 0) {
                int qt = sc.nextInt();

                if (qt == 1) {
                    int att = sc.nextInt();
                    g.push(att);
                } else if (qt == 2) {
                    g.pop(); // Just call pop(), do not print anything
                } else if (qt == 3) {
                    System.out.print(g.peek() + " "); // Print top element
                } else if (qt == 4) {
                    System.out.print(g.getMin() + " "); // Print minimum element
                }
            }
            System.out.println("\n~");
        }
        sc.close();
    }
}

// } Driver Code Ends


class Solution {
    private Stack<Long> stack;
    private long minElement;
    public Solution() {
        stack=new Stack<>();
    }

    // Add an element to the top of Stack
    public void push(int x) {
        // code here
        if(stack.isEmpty()){
            minElement=x;
            stack.push((long) x);
        }
        else{
            if(x<minElement){
                stack.push(2L* x-minElement);
                minElement=x;
            }else{
                stack.push((long) x);
            }
        }
    }

    // Remove the top element from the Stack
    public void pop() {
        // code here
        if(stack.isEmpty()) return;
        long top=stack.pop();
        if(top<minElement)
        {
            minElement=2* minElement-top;
            
        }
    }

    // Returns top element of the Stack
    public int peek() {
        // code here
        if(stack.isEmpty()) return -1;
        long top=stack.peek();
        return (top<minElement)? (int) minElement: (int)top;
    }

    // Finds minimum element of Stack
    public int getMin() {
        // code here
        return stack.isEmpty() ? -1: (int) minElement;
    }
}