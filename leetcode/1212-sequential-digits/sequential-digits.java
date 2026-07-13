import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result=new ArrayList<>();
        String digits="123456789";

        int minlen=String.valueOf(low).length();
        int maxlen=String.valueOf(high).length();

        for(int len=minlen;len<=maxlen;len++)
        {
            for(int start =0;start<=9-len;start++)
            {
                String sub=digits.substring(start,start+len);
                int num=Integer.parseInt(sub);

                if(num>=low && num<=high)

                {
                    result.add(num);
                }
            }
        }
        return result;
    }
}