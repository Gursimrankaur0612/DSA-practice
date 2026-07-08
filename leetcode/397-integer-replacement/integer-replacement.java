class Solution {
    public int integerReplacement(int n) {
        
        long num=n;
        int ops=0;

        while(num>1)
        {
            if((num & 1)==0)
            {
                num>>>=1;
            }
            else if(num==3 || (num & 3)==1)
            {
                num--;
            }
            else
            {
                num++;
            }
            ops++;
        }
        return ops;
    }
}