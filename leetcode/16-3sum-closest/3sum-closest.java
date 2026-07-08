class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest=nums[0]+nums[1]+nums[2];

        for(int i=0;i<=nums.length-3;i++)
        {
            int left=i+1;
            int right=nums.length-1;
            while(left<right)
            {
                int curr=nums[i]+nums[left]+nums[right];
                if(curr==target)
                {
                    return curr;
                }
                else if(Math.abs(curr-target) < Math.abs(closest-target))
                {
                    closest=curr;
                }
                if(curr<target)
                {
                    left++;
                }
                else
                {
                    right--;
                }
            }
        }
        return closest;
    }
}