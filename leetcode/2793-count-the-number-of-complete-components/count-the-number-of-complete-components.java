class Solution {
    public int countCompleteComponents(int n, int[][] edges) {

        List<List<Integer>> adj=new ArrayList<>();

        int[] degree=new int[n];

        for(int i=0;i<n;i++)
        {
            adj.add(new ArrayList<>());
        }

        for(int []edge:edges)
        {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
            
        }
        boolean []visited=new boolean[n];

        int complete=0;

        for(int i=0;i<n;i++)
        {
            if(!visited[i])
            {
                List<Integer> compnodes=new ArrayList<>();
                Queue<Integer> queue=new LinkedList<>();

                queue.offer(i);
                visited[i]=true;

                while(!queue.isEmpty())
                {
                    int curr=queue.poll();
                    compnodes.add(curr);

                    for(int neigh: adj.get(curr))
                    {
                        if(!visited[neigh])
                        {
                            visited[neigh]=true;
                            queue.offer(neigh);
                        }
                    }
                }
                int compsize=compnodes.size();
                boolean iscomp=true;

                for(int node: compnodes)
                {
                    if(degree[node]!=compsize-1)
                    {
                        iscomp=false;
                        break;
                    }
                }
                if(iscomp)
                {
                    complete++;
                }
            }
        }
        return complete;
    }
}