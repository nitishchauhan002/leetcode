class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the sum is greater than the maximum possible sum of digits, return ""
        if ((long) num * 9 < sum || sum < 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < num; i++) {
            // Take the maximum possible digit for the current position (up to 9)
            int digit = Math.min(9, sum);
            sb.append(digit);
            sum -= digit;
        }
        
        // If there is any remaining sum left, we couldn't fit it into 'num' digits
        if (sum > 0) {
            return "";
        }
        
        return sb.toString();
    }
}