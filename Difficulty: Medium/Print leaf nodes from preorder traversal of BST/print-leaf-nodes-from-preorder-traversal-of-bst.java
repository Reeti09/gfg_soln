import java.util.*;

class Node {
    int data;
    Node left, right;
    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> leafNodes(int[] preorder) {
        Node root = buildBST(preorder, 0, preorder.length - 1);
        ArrayList<Integer> leaves = new ArrayList<>();
        findLeaves(root, leaves);
        return leaves;
    }

    // Build BST from preorder traversal
    private Node buildBST(int[] pre, int start, int end) {
        if (start > end) return null;

        Node root = new Node(pre[start]);

        int splitIndex = start + 1;
        while (splitIndex <= end && pre[splitIndex] < pre[start]) {
            splitIndex++;
        }

        root.left = buildBST(pre, start + 1, splitIndex - 1);
        root.right = buildBST(pre, splitIndex, end);

        return root;
    }

    // Collect leaf nodes in preorder
    private void findLeaves(Node node, ArrayList<Integer> list) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            list.add(node.data);
        }

        findLeaves(node.left, list);
        findLeaves(node.right, list);
    }
}