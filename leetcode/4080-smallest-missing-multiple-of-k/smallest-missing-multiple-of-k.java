class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set=new HashSet<>();

        for(int num:nums)
        {
            set.add(num);
        }
        int cm=k;
        while(set.contains(cm))
        {
            cm+=k;
        }
        return cm;
    }
}