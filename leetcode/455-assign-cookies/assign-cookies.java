class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int childptr=0;
        int cookieptr=0;
        
        while(childptr < g.length && cookieptr < s.length)
        {
            if(s[cookieptr]>=g[childptr])
            {
                childptr++;
                cookieptr++;
            }
            else if(s[cookieptr]<g[childptr])
            {
                cookieptr++;
            }
        }
        return childptr;
    }
}