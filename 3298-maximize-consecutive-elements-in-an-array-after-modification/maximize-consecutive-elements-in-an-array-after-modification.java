import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int plusOne = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), plusOne));
            ans = Math.max(ans, dp.get(x + 1));

            int same = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, Math.max(dp.getOrDefault(x, 0), same));
            ans = Math.max(ans, dp.get(x));
        }

        return ans;
    }
}