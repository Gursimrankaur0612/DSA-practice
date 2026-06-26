class Solution {
    public int[][] transpose(int[][] matrix) {
        int n=matrix.length;
        int col=matrix[0].length;
        int res[][]=new int[col][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<col;j++)
            {
                res[j][i]=matrix[i][j];
            }
        }
        return res;
    }
}