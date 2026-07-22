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
    int ans=0;
    public boolean isBalanced(TreeNode root) {
       
     return maxdepth(root)!=-1;
       
    }
    private int maxdepth(TreeNode root)

    {
        if(root==null)return 0;

        int lefth=maxdepth(root.left);
        if(lefth==-1)return -1;

        int righth=maxdepth(root.right);
        if(righth==-1)return -1;

        ans=Math.abs(lefth-righth);
        if(ans>1)return -1;
        
        return Math.max(lefth,righth)+1;
    }
}