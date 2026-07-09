class Solution {
    public List<String> buildArray(int[] target, int n) {

        List<String> result=new ArrayList<>();
        int stream=1;

        for(int i=0;i<target.length;i++)
        {
            while(stream<target[i])
            {
                result.add("Push");
                result.add("Pop");
                stream++;
            }
            result.add("Push");
            stream++;
        }
        
        return result;
    }
}