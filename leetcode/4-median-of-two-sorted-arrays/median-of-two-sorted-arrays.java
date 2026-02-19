class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        int m=nums1.length;
        int n=nums2.length;
        int f=m+n;
        int[] res=new int[m+n];
        int i=0,j=0,k=0;
        while((i<m)&&(j<n))
        {
            if(nums1[i]<nums2[j])
            {
                res[k++]=nums1[i++];
            }
            else
            {
                res[k++]=nums2[j++];
            }
        }
        while (i < m) {
            res[k++] = nums1[i++];
        }

        while (j < n) {
            res[k++] = nums2[j++];
        }

       double median=0.0;
       
       int mid=f/2;
       if(f%2!=0)
       {
        
        return (double) res[mid];
       }
       else
       {
       return((res[mid-1]+res[mid])/2.0);

       }
    
    }
}