class Solution(object):
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        return len(s.rstrip().split()[-1]) if s.strip() else 0

# Example usage:
solution = Solution()
s = "Hello World  "
print(solution.lengthOfLastWord(s))  # Output: 5
