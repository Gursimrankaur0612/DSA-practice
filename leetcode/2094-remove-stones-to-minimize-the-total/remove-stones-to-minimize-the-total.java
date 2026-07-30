class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<piles.length;i++)
        {
            pq.add(piles[i]);
        }
        while(k>0)
        {
            int max=pq.poll();
            int ans= max-(max/2);
            pq.add(ans);
            k--;

        }
        
        int sum=0;
        while(!pq.isEmpty())
        {
            sum+=pq.poll();
        }
        return sum;
    }
}