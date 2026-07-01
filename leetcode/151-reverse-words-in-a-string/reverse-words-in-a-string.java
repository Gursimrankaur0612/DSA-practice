class Solution {
    public String reverseWords(String s) {
        
        if(s==null || s.length()==0) return "";
        int n=s.length();

        String words[]=s.trim().split("\\s+");

        StringBuilder a= new StringBuilder();

        for(int j=words.length-1;j>=0;j--)
        {
            a.append(words[j]);
            if(j>0)
            {
                a.append(" ");
            }
        }
    return a.toString();
    }
}