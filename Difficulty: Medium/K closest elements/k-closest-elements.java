
import java.util.*;

class Solution {
    int[] printKClosest(int[] arr, int k, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0]; // max-heap by distance
            return a[1] - b[1]; // if same dist, remove smaller first
        });

        for (int num : arr) {
            if (num == x) continue;
            int dist = Math.abs(num - x);
            pq.offer(new int[]{dist, num});
            if (pq.size() > k) pq.poll();
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) result.add(pq.poll()[1]);

        // Sort by closeness now (distance ASC, then value DESC)
        result.sort((a, b) -> {
            int d1 = Math.abs(a - x), d2 = Math.abs(b - x);
            return d1 != d2 ? d1 - d2 : b - a;
        });

        // Convert to array
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) ans[i] = result.get(i);
        return ans;
    }
}
