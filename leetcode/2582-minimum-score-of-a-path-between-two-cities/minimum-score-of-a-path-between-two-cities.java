class Solution {
    public int minScore(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph=new HashMap<>();
        for(int[] road: roads)
        {
            graph.computeIfAbsent(road[0], k-> new ArrayList<>()).add(new int[]{road[1],road[2]});
            graph.computeIfAbsent(road[1], k-> new ArrayList<>()).add(new int[]{road[0],road[2]});
        }
        int minscore=Integer.MAX_VALUE;
        boolean [] visited=new boolean[n+1];
        Queue<Integer> queue=new LinkedList<>();

        queue.offer(1);
        visited[1]=true;

        while(!queue.isEmpty())
        {
            int node=queue.poll();

            if(!graph.containsKey(node)) continue;

            for(int [] neighbor: graph.get(node))
            {
                int nextnode=neighbor[0];
                int distance=neighbor[1];

                minscore=Math.min(minscore, distance);

                if(!visited[nextnode])
                {
                    visited[nextnode]=true;
                    queue.offer(nextnode);
                }
            }
        }
        return minscore;
        
    }
}