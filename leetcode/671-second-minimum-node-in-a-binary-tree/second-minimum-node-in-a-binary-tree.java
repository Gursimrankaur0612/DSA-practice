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
    int first;
   long second=Long.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        first=root.val;
        dfs(root);
        if(second==Long.MAX_VALUE)
        {
            return -1;
        }
        return (int)second;

    }
    public void dfs(TreeNode root)
    {
        if(root==null)
        {
            return ;
        }
        if(root.val>first && root.val< second)
        {
            second=root.val;
        }
        dfs(root.left);
        dfs(root.right);
    }
}