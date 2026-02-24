/*
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    public Node delNode(Node root, int x) {
        if (root == null) return null;

        // 1. Navigate the tree
        if (x < root.data) {
            root.left = delNode(root.left, x);
        } else if (x > root.data) {
            root.right = delNode(root.right, x);
        } 
        // 2. Found the node to delete
        else {
            // Case 1 & 2: No child or only one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: Two children
            // Find the smallest node in the right subtree
            root.data = minValue(root.right);

            // Delete the successor
            root.right = delNode(root.right, root.data);
        }
        return root;
    }

    private int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }
}
