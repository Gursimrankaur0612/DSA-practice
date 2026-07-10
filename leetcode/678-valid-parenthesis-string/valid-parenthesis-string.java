class Solution {
    public boolean checkValidString(String s) {
        int bracket=0;
        int star=0;

        for(char c: s.toCharArray())
        {
            if(c=='(')
            {
                bracket++;
                star++;
            }
            else if(c==')')
            {
                bracket--;
                star--;

            }
            else
            {
                bracket--;
                star++;
            }

            if(bracket<0) bracket=0;

            if(star<0) return false;
        }


     return bracket==0;
    }
}