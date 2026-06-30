class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb= new StringBuilder();

        while(columnNumber>0)
        {
            columnNumber--;

            char leftchar=(char) ('A' +(columnNumber%26));

            sb.append(leftchar);

            columnNumber/=26;
        }
        return sb.reverse().toString();
    }
}