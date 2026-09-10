class Solution {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodeCount = 0;
        postOrder(root);
        return matchingNodeCount;
    }

    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; // {sum, count}
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int currentSum = node.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];

        if (currentSum / currentCount == node.val) {
            matchingNodeCount++;
        }

        return new int[]{currentSum, currentCount};
    }
}