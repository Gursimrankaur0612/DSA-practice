class Solution {
    public static int firstRepeated(int[] arr) {
        // code here
        int n=arr.length;
        int max=0;
        for(int i=0;i<n;i++)
        {
            if(arr[i]>max)
            {
                max=arr[i];
            }
        }
        boolean visited[]=new boolean[max+1];
        int firstrepeat=-1;
        
        for(int i=n-1;i>=0;i--)
        {
            if(visited[arr[i]]==true)
           { firstrepeat=i+1;
           
           }
            
            else
            visited[arr[i]]=true;
        }
        return firstrepeat;
    }
}
