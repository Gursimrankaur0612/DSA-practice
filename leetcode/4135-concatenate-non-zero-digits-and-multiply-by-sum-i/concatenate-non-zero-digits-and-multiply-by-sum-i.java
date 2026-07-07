class Solution {
    public long sumAndMultiply(int n) {
        long x=0;
        long sum=0;
        long product=1;
        while(n>0)
        {
            int digit=n%10;
            if(digit!=0)
            {
                x=x+(digit*product);
                sum+=digit;
                product*=10;
            }
            n=n/10;
        }
        return x*sum;
    }
}