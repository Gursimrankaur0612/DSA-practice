class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int [][]sorted=new int [n][2];
        for(int i=0;i<n;i++)
        {
            sorted[i][0]=nums[i];
            sorted[i][1]=i;
        }
        Arrays.sort(sorted,(a,b)->Integer.compare(a[0],b[0]));
        int [] sortedvalues=new int[n];

        for(int i=0;i<n;i++)
        {
            sortedvalues[i]=sorted[i][0];
        }

        int []sortedpos=new int[n];
        for(int i=0;i<n;i++)
        {
            sortedpos[sorted[i][1]]=i;
        }
        int LOG=18;
        int [][] up=new int[n][LOG];

        for(int i=0;i<n;i++)
        {
            int targetVal=sortedvalues[i]+maxDiff;

            int nextidx=upper_bound(sortedvalues,targetVal)-1;

            if(nextidx<=i)
            {
                up[i][0]=i;
            }
            else
            {
                up[i][0]=nextidx;
            }
        }
        for(int j=1;j<LOG;j++)
        {
            for(int i=0;i<n;i++)
            {
                up[i][j]=up[up[i][j-1]][j-1];
            }
        }
        int numQueries=queries.length;
        int ans[]=new int[numQueries];

        for(int q=0;q<numQueries;q++)
        {
            int u=queries[q][0];
            int v=queries[q][1];

            if(u==v)
            {
                ans[q]=0;
                continue;
            }
            if(nums[u]==nums[v])
            {
                ans[q]=1;
                continue;
            }

            int posu=sortedpos[u];
            int posv=sortedpos[v];

            if(sortedvalues[posu]>sortedvalues[posv])
            {
                int temp=posu;
                posu=posv;
                posv=temp;
            }
            int curr=posu;
            int steps=0;


            for(int j=LOG-1;j>=0;j--)
            {
                if(up[curr][j]<posv && up[curr][j]>curr)
                {
                    curr=up[curr][j];
                    steps+=(1<<j);
                }
            }
            curr =up[curr][0];
            steps++;

            if(curr<posv)
            {
                ans[q]=-1;
            }
            else
            {
                ans[q]=steps;
            }
        }
    return ans;
        
    }
    private int upper_bound(int arr[], int target)
    {
        int low=0;
        int high=arr.length;

        while(low<high)
        {
            int mid=low+(high-low)/2;
            if(arr[mid]<=target)
            {
                low=mid+1;
            }
            else
            {
                high=mid;
            }
        }
        return low;
    }
}