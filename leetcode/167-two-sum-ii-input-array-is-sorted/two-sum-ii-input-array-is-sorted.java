class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n=numbers.length-1;
        int left=0;
        int right=n;
        while(left<right)
        {
            int currsum=numbers[left]+numbers[right];
            if(currsum==target)
            {
                return new int[]{left+1,right+1};
            }
            else if(currsum<target)
            {
                left++;
            }
            else
            {
                right--;
            }

        }
        return new int[]{};
        
    }
}