/*
class Node {
    int data;
    Node left, right;

    public Node(int data){
        this.data = data;
    }
} */




class Solution {
    // Variables to store the maximum sum and maximum length found so far
    private int maxSum = 0;
    private int maxLen = 0;

    /**
     * Recursive helper function to traverse the tree and update maxSum and maxLen.
     *
     * @param node The current node being visited.
     * @param currentSum The sum of nodes from the root to the current node.
     * @param currentLen The length of the path from the root to the current node.
     */
    private void solve(Node node, int currentSum, int currentLen) {
        // Base case: if the node is null, return
        if (node == null) {
            return;
        }

        // Add current node's data to the current sum
        currentSum += node.data;
        // Increment the current path length
        currentLen++;

        // If it's a leaf node
        if (node.left == null && node.right == null) {
            // If this path is longer than the current maxLen
            if (currentLen > maxLen) {
                maxLen = currentLen;
                maxSum = currentSum;
            }
            // If this path has the same length but a greater sum
            else if (currentLen == maxLen && currentSum > maxSum) {
                maxSum = currentSum;
            }
            return; // No need to go further as it's a leaf
        }

        // Recursively call for left and right children
        solve(node.left, currentSum, currentLen);
        solve(node.right, currentSum, currentLen);
    }

    /**
     * Main function to find the sum of nodes on the longest path from root to leaf.
     *
     * @param root The root of the binary tree.
     * @return The maximum sum found on the longest path.
     */
    public int sumOfLongRootToLeafPath(Node root) {
        // Handle empty tree case
        if (root == null) {
            return 0;
        }

        // Initialize maxSum and maxLen before starting the traversal
        maxSum = 0;
        maxLen = 0;

        // Start the recursive traversal from the root with initial sum and length
        solve(root, 0, 0);

        return maxSum;
    }
}

