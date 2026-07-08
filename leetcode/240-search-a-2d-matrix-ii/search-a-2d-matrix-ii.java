class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        int n=matrix[0].length;
        int left=0;
        int right=n-1;

        while(left<m && right>=0)
        {
            if(matrix[left][right]==target)
            {
                return true;
            }
            else if(matrix[left][right]<target)
            {
                left++;
            }
            else
            {
                right--;
            }
        }
        
        return false;
    }
}