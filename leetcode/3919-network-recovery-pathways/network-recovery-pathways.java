class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n=online.length;

        List<int[]>[] graph=new ArrayList[n];
        for(int i=0;i<n;i++)
        {
            graph[i]=new ArrayList<>();
        }
        int [] globalInDegree=new int[n];
        for(int[] edge:edges)
        {
            int u=edge[0];
            int v=edge[1];

            int cost=edge[2];

            if(!online[u] || !online[v]) continue;
            graph[u].add(new int[]{v,cost});
            globalInDegree[v]++;
        }
        int [] toporder=new int[n];
        int topoidx=0;
        Queue<Integer> queue= new LinkedList<>();

        for(int i=0;i<n;i++)
        {
            if(online[i] && globalInDegree[i]==0)
            {
                queue.offer(i);
            }
        }
        while(!queue.isEmpty())
        {
            int u=queue.poll();
            toporder[topoidx++]=u;
            for(int []edge: graph[u])
            {
                int v=edge[0];
                globalInDegree[v]--;
                if(globalInDegree[v]==0)
                {
                    queue.offer(v);
                }
            }
        }
        int low=0;
        int high=1_000_000_000;
        int ans=-1;

        while(low<=high)
        {
            int mid=low+(high-low)/2;

            if(check(mid,n,graph,toporder,topoidx,k))
            {
                ans=mid;
                low=mid+1;
            }
            else
            {
                high=mid-1;
            }
        }
        return ans;
    }

    private boolean check(int mid,int n,List<int[]>[]graph,int []toporder,int toplen,long k)
    {
        long [] dp=new long[n];
        Arrays.fill(dp,Long.MAX_VALUE);
        dp[0]=0;

        for(int i=0;i<toplen;i++)
        {
            int u=toporder[i];
            if(dp[u]==Long.MAX_VALUE) continue;

            for(int [] edge:graph[u])
            {
                int v=edge[0];
                int cost=edge[1];

                if(cost>=mid)
                {
                    if(dp[u]+cost <dp[v])
                    {
                        dp[v]=dp[u]+cost;
                    }
                }
            }
        }
        return dp[n-1]<=k;
    }
}