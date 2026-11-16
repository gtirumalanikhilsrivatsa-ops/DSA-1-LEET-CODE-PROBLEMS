import java.util.HashMap;

class Solution {
    public long countBadPairs(int[] nums) {

        long goodPairs = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int key = nums[i] - i;

            int freq = map.getOrDefault(key, 0);

            goodPairs += freq;

            map.put(key, freq + 1);
        }

        long n = nums.length;
        long totalPairs = n * (n - 1) / 2;

        return totalPairs - goodPairs;
    }
}
