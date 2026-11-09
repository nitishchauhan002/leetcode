import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        int[] count = new int[k];
        
        // TreeSet to store currently available server IDs
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        // Min-heap to store busy servers as int[]: {free_time, server_id}
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        int maxRequests = 0;
        
        for (int i = 0; i < n; i++) {
            int currArrival = arrival[i];
            int currLoad = load[i];
            
            // Free up servers whose tasks have finished by the current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= currArrival) {
                available.add(busy.poll()[1]);
            }
            
            // If all servers are busy, drop this request
            if (available.isEmpty()) {
                continue;
            }
            
            // Find the preferred server using ceiling (>= i % k)
            Integer target = available.ceiling(i % k);
            
            // If no server >= i % k is available, wrap around to the first available server
            if (target == null) {
                target = available.first();
            }
            
            // Assign request to the chosen server
            available.remove(target);
            busy.offer(new int[] { currArrival + currLoad, target });
            
            count[target]++;
            maxRequests = Math.max(maxRequests, count[target]);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}