/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

*/
class Solution {
    /**
     * Checks if a binary tree is symmetric.
     *
     * @param root The root of the binary tree.
     * @return True if the tree is symmetric, false otherwise.
     */
    public boolean isSymmetric(Node root) {
        if (root == null) {
            return true; // An empty tree is considered symmetric
        }
        // Start the recursive helper function with the left and right children of the root
        return isMirror(root.left, root.right);
    }

    /**
     * Helper function to check if two subtrees are mirror images of each other.
     *
     * @param p The root of the first subtree.
     * @param q The root of the second subtree.
     * @return True if the two subtrees are mirror images, false otherwise.
     */
    private boolean isMirror(Node p, Node q) {
        // Base case 1: Both nodes are null, meaning they are symmetric (end of a branch)
        if (p == null && q == null) {
            return true;
        }
        // Base case 2: One node is null and the other is not, meaning they are not symmetric
        if (p == null || q == null) {
            return false;
        }
        // Base case 3: Values are different, meaning they are not symmetric
        if (p.data != q.data) { // Using 'data' as per your Node class
            return false;
        }

        // Recursive step:
        // For two trees to be mirror images:
        // 1. The left child of the first tree must be a mirror of the right child of the second tree.
        // 2. The right child of the first tree must be a mirror of the left child of the second tree.
        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }
}