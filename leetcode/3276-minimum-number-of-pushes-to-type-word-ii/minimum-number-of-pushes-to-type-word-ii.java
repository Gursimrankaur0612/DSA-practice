class Solution {
    public int minimumPushes(String word) {
        int freq[]=new int[26];

        for(char c: word.toCharArray())
        {
            freq[c-'a']++;
        }

        Arrays.sort(freq);

        int total=0;

        for(int i=0;i<26;i++)
        {
            int count=freq[25-i];
            if(count==0)break;

            int push=(i/8)+1;
           total+=count*push;
        }
        return total;
    }
}