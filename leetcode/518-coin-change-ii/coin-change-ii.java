class Solution {
    public int change(int amount, int[] coins) {

        int ans[]=new int[amount+1];

        ans[0]=1;

        for(int i:coins)
        {
            for(int j=i;j<=amount;j++)
            {
                ans[j]+=ans[j-i];
            }
        }
        return ans[amount];
    }
}