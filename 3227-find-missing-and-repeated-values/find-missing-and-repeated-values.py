class Solution(object):
    def findMissingAndRepeatedValues(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: List[int]
        """
        n = len(grid)
        total_numbers = n * n

        # Flatten the 2D grid into a 1D list
        nums = [num for row in grid for num in row]

        # Expected sums
        expected_sum = (total_numbers * (total_numbers + 1)) // 2
        expected_square_sum = (total_numbers * (total_numbers + 1) * (2 * total_numbers + 1)) // 6

        # Actual sums
        actual_sum = sum(nums)
        actual_square_sum = sum(num * num for num in nums)

        # Solve for missing (b) and repeated (a)
        sum_diff = actual_sum - expected_sum  # a - b
        square_sum_diff = actual_square_sum - expected_square_sum  # a^2 - b^2

        # Solve using the equations: a - b and (a^2 - b^2) = (a - b) * (a + b)
        a_plus_b = square_sum_diff // sum_diff
        a = (sum_diff + a_plus_b) // 2  # Repeated number
        b = a_plus_b - a  # Missing number

        return [a, b]