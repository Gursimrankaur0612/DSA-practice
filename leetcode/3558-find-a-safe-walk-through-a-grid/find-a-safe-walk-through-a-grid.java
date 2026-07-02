class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m=grid.size();
        int n=grid.get(0).size();

        int maxhealth[][]=new int[m][n];
        for(int [] row:maxhealth)
        {
            Arrays.fill(row,-1);

        }

        Deque<int[]> deque=new ArrayDeque<>();

        maxhealth[0][0]=health-grid.get(0).get(0);
        if(maxhealth[0][0]<=0)return false;
        deque.offerFirst(new int []{0,0});

        int dirs[]={0,1,0,-1,0};

        while(!deque.isEmpty())
        {
            int [] curr=deque.pollFirst();
            int r=curr[0];
            int c=curr[1];
            int currH = maxhealth[r][c];

            if(r==m-1 && c==n-1){
                return currH >=1;
            }

            for(int i=0;i<4;i++)
            {
                int nr=r+dirs[i];
                int nc=c+dirs[i+1];

                if(nr>=0 && nr< m && nc>=0 && nc<n)
                {
                    int nextH=currH -grid.get(nr).get(nc);

                    if(nextH> maxhealth[nr][nc] && nextH>=1)
                    {
                        maxhealth[nr][nc]=nextH;

                        if(grid.get(nr).get(nc)==0)
                        {
                            deque.offerFirst(new int []{nr, nc});
                        }
                        else
                        {
                            deque.offerLast(new int []{ nr, nc});
                        }
                    }
                }
            }
        }
        return false;
    }
}