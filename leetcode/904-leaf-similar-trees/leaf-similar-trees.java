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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1=new ArrayList<>();
        List<Integer> leaf2=new ArrayList<>();

        getleaves(root1,leaf1);
        getleaves(root2,leaf2);

        return leaf1.equals(leaf2);
    }

        private void getleaves(TreeNode node,List<Integer> ans)
        {

        if(node==null) return;

            if(node.left==null && node.right==null)
            {
                ans.add(node.val);
                return;
            }
            getleaves(node.right,ans);
            getleaves(node.left,ans);

        }
}