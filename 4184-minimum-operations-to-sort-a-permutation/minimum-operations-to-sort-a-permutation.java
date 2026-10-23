import java.util.Arrays;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int ans = Integer.MAX_VALUE;

        // Check Type 0: Rotation of increasing order (0, 1, 2, ..., n-1)
        boolean isIncRotation = true;
        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) zeroIdx = i;
            if (nums[i] != (nums[(i - 1 + n) % n] + 1) % n) {
                isIncRotation = false;
            }
        }

        if (isIncRotation) {
            // Rotating left zeroIdx times brings 0 to the front
            int cost = zeroIdx;
            // Alternatively: Reverse -> Rotate (n - zeroIdx) -> Reverse
            int altCost = 1 + (n - zeroIdx) % n + 1;
            ans = Math.min(ans, Math.min(cost, altCost));
        }

        // Check Type 1: Rotation of decreasing order (n-1, n-2, ..., 0)
        boolean isDecRotation = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[(i - 1 + n) % n] - 1 + n) % n) {
                isDecRotation = false;
            }
        }

        if (isDecRotation) {
            // Find where n-1 is located
            int maxValIdx = -1;
            for (int i = 0; i < n; i++) {
                if (nums[i] == n - 1) {
                    maxValIdx = i;
                    break;
                }
            }
            // Reverse first, then rotate
            int cost1 = 1 + maxValIdx;
            // Rotate first, then reverse
            int cost2 = (n - maxValIdx) % n + 1;
            ans = Math.min(ans, Math.min(cost1, cost2));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}