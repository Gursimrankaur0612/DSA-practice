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
/*class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums=new ArrayList<>();
        inorder(root,nums);
        int left=0;
        int right=nums.size()-1;
        while(left<right)
        {
            int sum=nums.get(left)+nums.get(right);
            if(sum==k)return true;
            if(sum<k)left++;
            else right--;
        }
        return false;
    }
    private void inorder(TreeNode root,List<Integer> nums)
    {
        if(root==null)return ;
        inorder(root.left,nums);
        nums.add(root.val);
        inorder(root.right,nums);
    }
}*/
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return helper(root, k, set);
    }
    
    private boolean helper(TreeNode node, int k, Set<Integer> set) {
        if (node == null) return false;
        
        // Check if the complement already exists in the set
        if (set.contains(k - node.val)) {
            return true;
        }
        
        // Add current value to set and recurse
        set.add(node.val);
        return helper(node.left, k, set) || helper(node.right, k, set);
    }
}
