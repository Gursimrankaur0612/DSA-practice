class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> set=new HashSet<>();

        while(n!=1 && !set.contains(n))
        {
            set.add(n);
            n=getsum(n);

        }
        return n==1;
    }

    private int getsum(int n)
    {
        int sum=0;
        while(n>0)
        {
            int digit=n%10;
            sum+=digit*digit;
            n=n/10;
        }
        return sum;
    }
}








/*class Solution {
    public boolean isHappy(int n) {
       while(n!=1 && n!=4)
       {
        int sq=0;
        while(n>0)
        {
            int digit=n%10;
            sq=sq+digit*digit;
            n=n/10;
        }
        n=sq;
    }
    return n==1;
    }
}*/