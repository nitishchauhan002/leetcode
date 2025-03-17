class Solution(object):
    def minCapability(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        left, right = min(nums), max(nums)

        def can_rob_with_capability(cap):
            count = 0
            i = 0
            while i < len(nums):
                if nums[i] <= cap:  # If we can rob this house
                    count += 1
                    i += 1  # Skip the next house since adjacent houses can't be robbed
                i += 1  # Move to the next house
            return count >= k  # Check if we can rob at least k houses

        # Binary search to find the minimum capability
        while left < right:
            mid = (left + right) // 2
            if can_rob_with_capability(mid):
                right = mid  # Try a smaller capability
            else:
                left = mid + 1  # Increase the capability

        return left  # The minimum capability found
