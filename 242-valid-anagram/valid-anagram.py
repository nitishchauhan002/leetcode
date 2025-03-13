from collections import Counter

class Solution(object):
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        return Counter(s) == Counter(t)

# Example usage:
solution = Solution()
s = "anagram"
t = "nagaram"
print(solution.isAnagram(s, t))  # Output: True

s = "rat"
t = "car"
print(solution.isAnagram(s, t))  # Output: False
