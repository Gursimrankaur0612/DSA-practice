class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int downOne=0;
        int downTwo=0;

        for(int i=2;i<=cost.length;i++)
        {
            int currCost=Math.min(downOne+cost[i-1], downTwo+cost[i-2]);

            downTwo=downOne;
            downOne=currCost;
        }
        return downOne;
    }
}