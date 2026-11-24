class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long gcdVal = 0;
            long lcmVal = 1;

            for (int j = i; j < n; j++) {
                long num = nums[j];
                prod *= num;
                gcdVal = gcd(gcdVal, num);
                lcmVal = lcm(lcmVal, num);

                // Stop early if product already exceeds maximum possible lcm * gcd
                if (prod > 2520L * gcdVal) {
                    break;
                }

                if (prod == lcmVal * gcdVal) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}