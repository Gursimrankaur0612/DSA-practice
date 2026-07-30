class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<gifts.length;i++)
        {
            pq.add(gifts[i]);
        }
        
        while(k>0)
        {

            int max=pq.poll();

            int rem = (int) Math.floor(Math.sqrt(max));
            pq.add(rem);
            k--;
        }
        long total=0;
        while(!pq.isEmpty())
        {
            total+=pq.poll();
        }
        return total;
    }
}