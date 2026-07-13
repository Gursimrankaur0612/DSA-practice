class Solution {
    public String frequencySort(String s) {
        HashMap <Character,Integer> map=new HashMap<>();

        for(char c:s.toCharArray())
        {
            if(map.containsKey(c))
            {
            map.put(c,map.getOrDefault(c,0)+1); // if laready present incremenet frequency
            }
            else
            {
                map.put(c,1);
            }
        }
            String ans="";
            while(!map.isEmpty())
            {
                char maxch=' ';
                int max=0;
                ArrayList<Character> list=new ArrayList<>(map.keySet());
                {
                    for(int i=0;i<list.size();i++)
                    {
                        char x=list.get(i);
                        if(map.get(x)>max)
                        {
                            max=map.get(x);
                            maxch=x;
                        }
                    }
                    for(int i=0;i<max;i++)
                    {
                        ans=ans+maxch;
                    }
                    map.remove(maxch);
                }

            }
        return ans;
    }
}