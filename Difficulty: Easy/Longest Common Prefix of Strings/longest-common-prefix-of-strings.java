class Solution {
    public String longestCommonPrefix(String arr[]) {
        if(arr.length==0 || arr==null)return "";
        Arrays.sort(arr);
        // code here
        String first=arr[0];
        String last=arr[arr.length-1];
        int i=0;
        
        while(i<first.length() && i<last.length() && first.charAt(i)==last.charAt(i))
        {
            i++;
        }
        return first.substring(0,i);
    }
}