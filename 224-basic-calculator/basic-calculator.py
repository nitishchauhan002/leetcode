class Solution(object):
    def calculate(self, s):
        """
        :type s: str
        :rtype: int
        """
        s = s.replace(" ", "")  # Remove spaces
        stack, num, sign = [], 0, 1
        result = 0

        for char in s:
            if char.isdigit():
                num = num * 10 + int(char)  # Form the number
            elif char in "+-":
                result += sign * num  # Apply the previous sign
                num = 0  # Reset number
                sign = 1 if char == '+' else -1  # Update sign
            elif char == "(":
                stack.append(result)  # Store current result
                stack.append(sign)  # Store current sign
                result, sign = 0, 1  # Reset for new expression
            elif char == ")":
                result += sign * num  # Apply last number
                num = 0  # Reset number
                result *= stack.pop()  # Apply sign before '('
                result += stack.pop()  # Add previous result

        return result + sign * num