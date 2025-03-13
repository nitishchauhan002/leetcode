import re

class Solution(object):
    def isPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        # Remove non-alphanumeric characters and convert to lowercase
        cleaned_s = re.sub(r'[^a-zA-Z0-9]', '', s).lower()
        # Check if the cleaned string is the same forwards and backwards
        return cleaned_s == cleaned_s[::-1]

# Example usage:
solution = Solution()
s = "A man, a plan, a canal: Panama"
print(solution.isPalindrome(s))  # Output: True

s = "race a car"
print(solution.isPalindrome(s))  # Output: False
