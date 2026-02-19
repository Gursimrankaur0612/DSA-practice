class Solution {
    public int[] searchRange(int[] nums, int target) 
    {
    int first=findrange(nums,target,true);
    if(first==-1)return new int[]{-1,-1};

    int last=findrange(nums,target,false);
    return new int[]{first,last};
    }


    public int findrange(int [] nums,int target,boolean isFirst){
        int l=0;
        int r=nums.length-1;
        int res=-1;
        while(l<=r)
        {
            int mid=l+(r-l)/2;
           if(nums[mid]<target)
            {
                l=mid+1;
            }
            else if(nums[mid]>target)
            {
                r=mid-1;
            }
            else if(nums[mid] == target) {
                res = mid;
                if (isFirst) r = mid - 1;
                else
                 l = mid + 1;
            }
        }
        return res;
    }
}

/*class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findrange(nums, target, true);
        if (first == -1) return new int[]{-1, -1};
        int last = findrange(nums, target, false);
        return new int[]{first, last};
    }

    public int findrange(int[] nums, int target, boolean isFirst) {
        int l = 0;
        int r = nums.length - 1;
        int res = -1;
        while (l <= r) {
            // FIX: Correct parentheses to avoid infinite loop and overflow
            int mid = l + (r - l) / 2; 
            
            if (nums[mid] == target) {
                res = mid;
                if (isFirst) r = mid - 1;
                else l = mid + 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }
}*/
