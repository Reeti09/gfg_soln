//{ Driver Code Starts
// Initial Template for Java

// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class MaxSum {
    static Node buildTree(String str) {

        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= ip.length) break;

            currVal = ip[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            String s = br.readLine();
            Node root = buildTree(s);
            Solution ob = new Solution();
            int ans = ob.getMaxSum(root);
            System.out.println(ans);
            t--;

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java
/*class Node
{
    int data;
    Node left, right;

    Node(int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}*/

class Solution {
    // Function to return the maximum sum of non-adjacent nodes.
    public int getMaxSum(Node root) {
        Pair ans = helper(root);
        return Math.max(ans.incl, ans.excl);
    }

    // Helper class to store two values: 
    // incl -> max sum including current node
    // excl -> max sum excluding current node
    static class Pair {
        int incl, excl;
        Pair(int incl, int excl) {
            this.incl = incl;
            this.excl = excl;
        }
    }

    private Pair helper(Node root) {
        if (root == null) {
            return new Pair(0, 0);
        }

        Pair left = helper(root.left);
        Pair right = helper(root.right);

        // If we include current node, we cannot include its children
        int incl = root.data + left.excl + right.excl;

        // If we exclude current node, we can take max of including or excluding children
        int excl = Math.max(left.incl, left.excl) + Math.max(right.incl, right.excl);

        return new Pair(incl, excl);
    }
}

