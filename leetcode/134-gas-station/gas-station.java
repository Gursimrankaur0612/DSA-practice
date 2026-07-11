class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total=0;
        int currenttank=0;
        int start=0;

        for(int i=0;i<gas.length;i++)
        {
            int netgas=gas[i]-cost[i];
            total+=netgas;
            currenttank+=netgas;

            if(currenttank<0)
            {
                start=i+1;
                currenttank=0;
            }
        }
        return total>=0?start:-1;
    }
}