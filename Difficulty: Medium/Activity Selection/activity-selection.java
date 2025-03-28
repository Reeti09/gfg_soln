//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine().trim());

        while (t-- > 0) {
            // Read the start times
            String[] startInput = reader.readLine().trim().split("\\s+");
            int[] start = new int[startInput.length];
            for (int i = 0; i < startInput.length; i++) {
                start[i] = Integer.parseInt(startInput[i]);
            }

            // Read the end times
            String[] endInput = reader.readLine().trim().split("\\s+");
            int[] finish = new int[endInput.length];
            for (int i = 0; i < endInput.length; i++) {
                finish[i] = Integer.parseInt(endInput[i]);
            }

            // Create solution object and call activitySelection
            Solution obj = new Solution();
            System.out.println(obj.activitySelection(start, finish));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends



class Solution {
    public int activitySelection(int[] start, int[] finish) {
        int n = start.length;
        List<int[]> activities = new ArrayList<>();

        // Store activities as (end time, start time) pairs
        for (int i = 0; i < n; i++) {
            activities.add(new int[]{finish[i], start[i]});
        }

        // Sort activities by end time (greedy approach)
        activities.sort(Comparator.comparingInt(a -> a[0]));

        int count = 1; // Select the first activity
        int lastEnd = activities.get(0)[0]; // Track the last selected activity's end time

        // Iterate through sorted activities
        for (int i = 1; i < n; i++) {
            if (activities.get(i)[1] > lastEnd) { // Ensure non-overlapping activities
                count++;
                lastEnd = activities.get(i)[0]; // Update lastEnd to current activity's end time
            }
        }
        return count;
    }


}

