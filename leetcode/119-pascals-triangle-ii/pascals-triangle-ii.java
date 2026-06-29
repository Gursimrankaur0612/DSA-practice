class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row= new ArrayList<>();
        long c=1;
        row.add((int)c);

        for(int i=1;i<=rowIndex;i++)
        {
            c=c*(rowIndex-i+1)/i;
            row.add((int)c);
        }

        return row;
    }
}