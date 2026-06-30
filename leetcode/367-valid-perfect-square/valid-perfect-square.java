/*class Solution {
    public boolean isPerfectSquare(int num) {
        if(num<1)return false;

        int left=1;
        int right=num;
      

        while(left<=right)
        {
        long mid=left+(right-left)/2;
        long square=mid*mid;
            if(square==num)
                {
                    return true;
                }
            else if(square<num)
                {
                    left=(int)mid+1;
                }
            else
            {
                right=(int)mid-1;
            }
            
        }
        return false;
    }
}*/
class Solution {
    public boolean isPerfectSquare(int num) {
        int odd = 1;
        while (num > 0) {
            num -= odd;
            odd += 2;
        }
        return num == 0;
    }
}