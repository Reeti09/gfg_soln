/*
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}
*/

class Solution {
    // Main function to check for a dead end
    public boolean isDeadEnd(Node root) {
        // We start with a range [1, Integer.MAX_VALUE] for possible insertions.
        // The 'min' for insertion check should be 0 because 1-1=0, and we can't insert 0.
        // The 'max' for insertion check should be Integer.MAX_VALUE initially.
        return checkDeadEnd(root, 0, Integer.MAX_VALUE);
    }

    // Helper recursive function
    private boolean checkDeadEnd(Node node, int minAllowed, int maxAllowed) {
        // Base Case 1: If the current node is null, we've successfully traversed
        // this path without finding a dead end.
        if (node == null) {
            return false;
        }

        // Base Case 2: If it's a leaf node
        if (node.left == null && node.right == null) {
            // A dead end occurs if the range (minAllowed, maxAllowed)
            // allows only the current node's value.
            // This means minAllowed is node.data - 1 AND maxAllowed is node.data + 1.
            // Example: If minAllowed = 0, maxAllowed = 2, and node.data = 1.
            // We cannot insert 0 (because minAllowed is 0, implying 0 is "occupied" or disallowed)
            // We cannot insert 2 (because maxAllowed is 2, implying 2 is "occupied" or disallowed)
            // So, only 1 fits.
            if (minAllowed == node.data - 1 && maxAllowed == node.data + 1) {
                return true;
            }
            // Special case for node.data = 1: minAllowed will be 0.
            // If maxAllowed is 2, then 1 is a dead end (cannot insert 0 or 2).
            // This case is implicitly covered by the above condition if minAllowed starts at 0.
            // If minAllowed was strictly greater than 0 for insertion checks, we'd need
            // a specific check like: if (node.data == 1 && maxAllowed == 2) return true;
            // But with minAllowed = 0, data-1 = 0, so it works.
        }

        // Recursive Step: Traverse left and right subtrees
        // For the left child, the new upper bound (maxAllowed) becomes node.data
        if (checkDeadEnd(node.left, minAllowed, node.data)) {
            return true;
        }

        // For the right child, the new lower bound (minAllowed) becomes node.data
        if (checkDeadEnd(node.right, node.data, maxAllowed)) {
            return true;
        }

        // No dead end found in this subtree
        return false;
    }
}