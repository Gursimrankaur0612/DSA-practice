class Solution {
    public boolean sumGame(String num) {
        int n=num.length();
        double diff=0;

        for(int i=0;i<n;i++)
        {
            char c=num.charAt(i);
            int sign=(i<n/2)?1:-1;

            if(c=='?')
            {
                diff+=sign*4.5;
            }
            else
            {
                diff+=sign*(c-'0');
            }
        }
        return diff!=0;
    }
}