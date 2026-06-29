class Solution {
    public int singleNumber(int[] nums) {
        int result=0;
        for(int num:nums)
        {
            result^=num; //XOR OF EVERY SAME ELEMENT WILL BE ZERO IF THERE IS UNIQUE IT WILL BE LEFT BEHIND;
        }
        return result;
    }
}