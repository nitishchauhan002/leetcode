class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aAlice = calculateDayOfYear(arriveAlice);
        int lAlice = calculateDayOfYear(leaveAlice);
        int aBob = calculateDayOfYear(arriveBob);
        int lBob = calculateDayOfYear(leaveBob);
        
        // Latest arrival and earliest leaving define the overlapping period
        int startOverlap = Math.max(aAlice, aBob);
        int endOverlap = Math.min(lAlice, lBob);
        
        // If startOverlap <= endOverlap, they are together for (end - start + 1) days
        return Math.max(0, endOverlap - startOverlap + 1);
    }
    
    private int calculateDayOfYear(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int dayOfYear = day;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonths[i];
        }
        return dayOfYear;
    }
}