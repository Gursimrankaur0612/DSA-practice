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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        if(root==null)return ans;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);

        int level=0;

        while(!queue.isEmpty())
        {
            int size=queue.size();
            List<Integer> l=new ArrayList<>();

            for(int i=0;i<size;i++)
            {
                TreeNode curr=queue.poll();
                if(level%2==0)
                {
                    l.add(curr.val);
                }
                else
                {
                    l.add(0,curr.val);
                }

                if(curr.left!=null)
                {
                    queue.offer(curr.left);
                }
                if(curr.right!=null)
                {
                    queue.offer(curr.right);
                }
            }
            ans.add(l);
            level++;
        }
        return ans;
    }
}