class GFG {
    ArrayList<Integer> find(int arr[], int x) {
        int first = -1, last = -1;
        int l = 0, r = arr.length - 1;

       
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= x) {
                if (arr[mid] == x) first = mid;
                r = mid - 1;
            } else l = mid + 1;
        }

       
        l = 0; r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= x) {
                if (arr[mid] == x) last = mid;
                l = mid + 1;
            } else r = mid - 1;
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(first);
        result.add(last);
        return result;
    }
}
