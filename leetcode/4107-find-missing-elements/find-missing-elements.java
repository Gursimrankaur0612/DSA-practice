class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer> missing = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 1; i++) {
            // Fill in all numbers strictly between nums[i] and nums[i+1]
            for (int val = nums[i] + 1; val < nums[i + 1]; val++) {
                missing.add(val);
            }
        }
        
        return missing;
    }
}