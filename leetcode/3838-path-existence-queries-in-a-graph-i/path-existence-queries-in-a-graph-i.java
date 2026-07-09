class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int [] comp=new int[n];
        int current=0;
        comp[0]=current;

        for(int i=1;i<n;i++)
        {
            if(nums[i]-nums[i-1]>maxDiff)
            {
                current++;
            }
            comp[i]=current;
        }
        int q=queries.length;
        boolean[]ans=new boolean[q];
        for(int i=0;i<q;i++)
        {
            int u=queries[i][0];
            int v=queries[i][1];
            ans[i]=(comp[u]==comp[v]);
        }
        return ans;
    }
}