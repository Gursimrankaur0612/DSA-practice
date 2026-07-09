class Solution {
    public int[] sortArray(int[] nums)
     {
       MergeSort(nums,0,nums.length-1);
       return nums;
    }
    
      public void MergeSort(int[] nums,int left,int right) {
        if(left<right)
        {
            int mid=left+(right-left)/2;
            MergeSort(nums,left,mid);
         
            MergeSort(nums,mid+1,right);

            Merge(nums,left,mid,right);
        }
        
    }
    private static void Merge(int []nums, int left, int mid, int right)
    {
        int l=mid-left+1;
        int r=right-mid;

        int[] L=new int[l];
        int[] R=new int[r];

        for(int i=0;i<l;i++)
        {
            L[i]=nums[left+i];
        }
        for(int j=0;j<r;j++)
        {
            R[j]=nums[mid+1+j];
        }

        int i=0;
        int j=0;
        int k=left;
        while(i<l && j<r)
        {
            if(L[i]<R[j])
            {
                nums[k]=L[i];
                i++;
            }
            else
            {
                nums[k]=R[j];
                j++;
            }
            k++;
        }

        while(i<l)
        {
            nums[k]=L[i];
            i++;
            k++;
        }
        while(j<r)
        {
            nums[k]=R[j];
            j++;
            k++;
        }
    }
}