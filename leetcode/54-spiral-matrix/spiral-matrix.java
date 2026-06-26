class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        if(matrix==null ||matrix.length==0)return new ArrayList<>();

        int m=matrix.length;
        int n=matrix[0].length;
        List<Integer> result=new ArrayList<>();
        int top=0;
        int bottom=m-1;
        int left=0;
        int right=n-1;
        while(top<=bottom && left<=right)
        {
            for(int j=left;j<=right;j++)
            {
                result.add(matrix[top][j]);
            }
        top++;
        for(int i=top;i<=bottom;i++)
        {
            result.add(matrix[i][right]);
        }right--;
        if(top<=bottom)
        {
            
            for(int i=right;i>=left;i--)
            {
                result.add(matrix[bottom][i]);
            }bottom--;
            
        }
        if(left<=right){
            for(int j=bottom;j>=top;j--)
            {
                result.add(matrix[j][left]);
            }left++;
        }
        }
       return result;
        
    }
}