/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int absdiff=Integer.MAX_VALUE;
    private Integer prev=null;
    public int getMinimumDifference(TreeNode root) {
        
        inorder(root);
        return absdiff;
        }
        private void inorder(TreeNode root)
        {
            if(root==null)return;

            inorder(root.left);
            if(prev!=null)
            {
                absdiff=Math.min(absdiff,Math.abs(prev-root.val));
            }
            prev=root.val;
            inorder(root.right);
        }
}