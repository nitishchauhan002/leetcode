class Solution(object):
    def maximumTripletValue(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        n = len(nums)
        if n < 3:
            return 0  # Not enough elements for a triplet

        # Step 1: Precompute the maximum suffix values
        max_suffix = [0] * n
        max_suffix[-1] = nums[-1]

        for k in range(n - 2, -1, -1):
            max_suffix[k] = max(max_suffix[k + 1], nums[k])

        # Step 2: Compute the maximum triplet value
        max_value = float('-inf')

        for j in range(1, n - 1):
            for i in range(j):
                triplet_value = (nums[i] - nums[j]) * max_suffix[j + 1]
                max_value = max(max_value, triplet_value)

        return max_value if max_value > 0 else 0
