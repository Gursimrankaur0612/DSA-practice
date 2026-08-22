class Solution {
    public boolean checkDivisibility(int n) {
        int a=n;
        int sum=0;
        int prd=1;
        while(n>0)
        {
            int x= n % 10;
            sum+=x;
            prd*=x;
            n=n/10;
        }
        int s=sum+prd;
        if(a%s==0 && s!=0)return true;
        else return false;
    }
}