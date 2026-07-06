class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins==null || amount<0) return -1;
        if(amount==0)return 0;

        int array[]=new int[amount+1];

        array[0]=0;

        for(int i=1;i<=amount;i++)
        {
            array[i]=amount+1;
            for(int j=0;j<coins.length;j++)
            {
                if(coins[j]<=i)
                {
                    if(array[i]>=array[i-coins[j]]+1)
                    {
                        array[i]=array[i-coins[j]]+1;
                    }
                }
            }
        }
        if(array[amount]>amount)
        {
            return -1;
        }
        else{
        return array[amount];
        }
    }
}