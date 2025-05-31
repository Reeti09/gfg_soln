/*
class Node {
    int data;
    Node left, right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/
class Solution {
    /**
     * Finds the greatest number in the BST that is less than or equal to k.
     *
     * @param root The root of the Binary Search Tree.
     * @param k    The target value.
     * @return The greatest number in the BST <= k, or -1 if no such number exists.
     */
    public int findMaxFork(Node root, int k) {
        int ans = -1; // Initialize with -1 to signify no such element found yet.

        // Traverse the BST
        while (root != null) {
            if (root.data == k) {
                // Found an exact match, which is the best possible answer
                return root.data;
            } else if (root.data < k) {
                // Current node's data is less than k.
                // This is a potential candidate for our answer.
                ans = root.data;
                // Since we want the *greatest* value <= k,
                // try to find a larger value in the right subtree.
                root = root.right;
            } else { // root.data > k
                // Current node's data is greater than k.
                // We need smaller values, so move to the left subtree.
                root = root.left;
            }
        }
        return ans; // Return the greatest value found that was <= k, or -1 if none.
    }
}