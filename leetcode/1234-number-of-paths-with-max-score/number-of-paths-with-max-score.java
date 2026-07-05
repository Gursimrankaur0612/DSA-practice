class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n=board.size();
        int MOD=1_000_000_007;

        int [][] maxscore=new int[n][n];
        int [][] paths=new int[n][n];

        paths[n-1][n-1]=1;

        int[][]dirs={{1,0},{0,1},{1,1}};
        
        for(int i=n-1;i>=0;i--)
        {
            for(int j=n-1;j>=0;j--)
            {
                if(board.get(i).charAt(j)=='X' || (i==n-1 && j==n-1))
                {
                    continue;
                }
                int currentval=0;
                if(board.get(i).charAt(j)!='E')
                {
                    currentval=board.get(i).charAt(j)-'0';
                }
                int maxprev=-1;
                int maxprevpath=0;

                for(int [] dir: dirs)
                {
                    int prevI=i+dir[0];
                    int prevJ=j+dir[1];

                    if(prevI< n && prevJ < n &&paths[prevI][prevJ]>0)
                    {
                        if(maxscore[prevI][prevJ]>maxprev)
                        {
                            maxprev=maxscore[prevI][prevJ];
                            maxprevpath=paths[prevI][prevJ];
                        }
                        else if(maxscore[prevI][prevJ]==maxprev)
                        {
                            maxprevpath=(maxprevpath+paths[prevI][prevJ])%MOD;
                        }
                    }
                }
                if(maxprev!=-1)
                {
                    maxscore[i][j]=maxprev+currentval;
                    paths[i][j]=maxprevpath;
                }
            }

        }
        return new int[]{maxscore[0][0], paths[0][0]};
        
    }
}