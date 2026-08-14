class Solution {
    public int maximumLengthSubstring(String s) {
        
        int [] freq=new int[26];

        int left=0;
        int maxlength=0;
        
        for(int right=0;right < s.length();right++)
        {
            char rightchar=s.charAt(right);
            freq[rightchar- 'a']++;

            while(freq[rightchar - 'a'] > 2)
            {
                char leftchar=s.charAt(left);
                freq[leftchar - 'a']--;
                left++;
            }
            maxlength=Math.max(maxlength,right-left+1);
        }
    return maxlength;

    }
}