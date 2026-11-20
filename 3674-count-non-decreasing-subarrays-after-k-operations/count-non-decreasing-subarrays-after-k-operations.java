import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    private static class Segment {
        int start;
        int end;
        long val;

        Segment(int start, int end, long val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }

    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long ops = 0;
        int r = n - 1;

        // Deque ordered from left (smaller indices) to right (larger indices)
        Deque<Segment> dq = new ArrayDeque<>();

        for (int l = n - 1; l >= 0; l--) {
            int newStart = l;
            int newEnd = l;
            long targetVal = nums[l];

            // Elements to the right that are <= nums[l] become >= nums[l] once raised to nums[l]
            while (!dq.isEmpty() && dq.peekFirst().val <= targetVal) {
                Segment seg = dq.pollFirst();
                long len = seg.end - seg.start + 1L;
                ops += len * (targetVal - seg.val);
                newEnd = seg.end;
            }

            dq.offerFirst(new Segment(newStart, newEnd, targetVal));

            // Shrink window from the right while total operations exceed k
            while (ops > k && r >= l) {
                Segment last = dq.peekLast();
                // Each element at index r was raised from nums[r] to last.val
                long extraForR = last.val - nums[r];
                ops -= extraForR;

                if (last.start == last.end) {
                    dq.pollLast();
                } else {
                    last.end--;
                }
                r--;
            }

            ans += (r - l + 1L);
        }

        return ans;
    }
}