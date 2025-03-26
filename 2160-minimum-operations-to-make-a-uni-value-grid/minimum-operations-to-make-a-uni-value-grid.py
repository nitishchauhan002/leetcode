class Solution(object):
    def minOperations(self, grid, x):
        """
        :type grid: List[List[int]]
        :type x: int
        :rtype: int
        """
        flat_list = [num for row in grid for num in row]
        flat_list.sort()
        median = flat_list[len(flat_list) // 2]
        
        operations = 0
        for num in flat_list:
            diff = abs(num - median)
            if diff % x != 0:
                return -1
            operations += diff // x
        
        return operations
