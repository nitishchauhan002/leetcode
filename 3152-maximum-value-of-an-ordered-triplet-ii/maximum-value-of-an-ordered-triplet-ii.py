class Solution(object):
    def maximumTripletValue(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        n = len(nums)
        if n < 3:
            return 0
        
        max_i = nums[0]
        max_ij = float('-inf')
        max_value = float('-inf')
        
        for j in range(1, n - 1):
            max_ij = max(max_ij, max_i - nums[j])
            max_value = max(max_value, max_ij * nums[j + 1])
            max_i = max(max_i, nums[j])
        
        return max_value if max_value > 0 else 0