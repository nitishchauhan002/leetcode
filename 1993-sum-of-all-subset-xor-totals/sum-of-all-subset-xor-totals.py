class Solution(object):
    def subsetXORSum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        def dfs(index, current_xor):
            if index == len(nums):
                return current_xor
            # Include nums[index]
            include = dfs(index + 1, current_xor ^ nums[index])
            # Exclude nums[index]
            exclude = dfs(index + 1, current_xor)
            return include + exclude
        
        return dfs(0, 0)
