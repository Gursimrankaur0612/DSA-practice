class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result=new ArrayList<>();
        if(s.length()<p.length())return result;

        int [] freq=new int[26];
        for(char c: p.toCharArray())freq[c-'a']++;
        int left=0;
        int right=0;
        int count=p.length();
        while(right<s.length())
        {
            if(freq[s.charAt(right++)-'a']-->=1)
            {
                count--;
            }
            if(count==0)result.add(left);
            if(right-left==p.length())
            {
                if(freq[s.charAt(left++)-'a']++>=0)
                count++;
            }
        }

        return result;
    }
}