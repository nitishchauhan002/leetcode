class Solution {
    public int minimumRecolors(String blocks, int k) {
        int minOps = k; // Maximum recoloring needed initially
        int whiteCount = 0;

        // Count white blocks in the first window of size k
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
        }

        minOps = whiteCount; // Store initial result

        // Slide the window across the string
        for (int i = k; i < blocks.length(); i++) {
            // Remove the leftmost character of the previous window
            if (blocks.charAt(i - k) == 'W') {
                whiteCount--;
            }
            // Add the rightmost character of the new window
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
            // Update minimum operations
            minOps = Math.min(minOps, whiteCount);
        }

        return minOps;
    }
}