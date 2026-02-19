class Solution {
    public List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> result=new ArrayList<>();
        backtrack(result,new ArrayList<>(),nums,new boolean[nums.length]);
        return result;
    }
    private void backtrack(List<List<Integer>> result,List<Integer>templist,int [] nums,boolean []used)
    {
       if(templist.size()==nums.length)
       {
        result.add(new ArrayList<>(templist));
       }
        for(int i=0;i<nums.length;i++)
        {
            if(used[i])continue;
            used[i]=true;
            templist.add(nums[i]);
            backtrack(result,templist,nums,used);
            used[i]=false;
           templist.remove(templist.size()-1);
        }
    }
}