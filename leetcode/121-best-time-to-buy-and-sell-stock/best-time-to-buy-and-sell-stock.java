
class Solution {
    public int maxProfit(int[] prices) {
        
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int maxProfit = 0;
        int minPrice = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            
            if (currentPrice > minPrice) {
            
                int potentialProfit = currentPrice - minPrice;
               
                if (potentialProfit > maxProfit) {
                    maxProfit = potentialProfit;
                }
            } else {
               
                minPrice = currentPrice;
            }
        }
        
        return maxProfit;
    }
}





//gives time complexity n^2
/*class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int diff=0;
        for(int i=0;i<n-1;i++)
        {
            for(int j=i+1;j<n;j++)
            {
            int curr=prices[j]-prices[i];
            if(curr>diff)
            {
                diff=curr;
            }

           }
        }
        return diff;
    }
}*/