class Solution {
    public int climbStairs(int n) {
         int a[]=new int[n+1];
            return recur(n,a);
    }


        private int recur(int n, int[]a)
        {
        if(n<=2){
            return n;
        }
       
        if(a[n]!=0)
        {
            return a[n];
        }
       a[n]= recur(n-1,a)+recur(n-2,a);
       return a[n];
    }
}