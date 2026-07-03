class Solution {
    public int rob(int[] nums) {

        int money=0;
        int a=0;
        int b=0;

        for(int i=0;i<nums.length;i++)
        {
            money= Math.max(a, b+nums[i]);
            b=a;
            a=money;
        }
        return a;
    }
}