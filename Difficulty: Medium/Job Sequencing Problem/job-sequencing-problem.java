//{ Driver Code Starts
// Initial Template for Java
import java.util.*;


// } Driver Code Ends

import java.util.*;

class Solution {
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = deadline.length;
        Job[] jobs = new Job[n];

        // Step 1: Create job objects
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(deadline[i], profit[i]);
        }

        // Step 2: Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 3: Find the maximum deadline
        int maxDeadline = 0;
        for (int d : deadline) {
            maxDeadline = Math.max(maxDeadline, d);
        }

        // Step 4: Create a time slot array
        boolean[] slot = new boolean[maxDeadline + 1]; // 1-based index
        int maxProfit = 0, countJobs = 0;

        // Step 5: Schedule jobs
        for (Job job : jobs) {
            // Find the latest available slot (starting from job.deadline)
            for (int j = job.deadline; j > 0; j--) {
                if (!slot[j]) { // If slot is empty, assign job
                    slot[j] = true;
                    countJobs++;
                    maxProfit += job.profit;
                    break;
                }
            }
        }

        // Step 6: Return the result
        ArrayList<Integer> result = new ArrayList<>();
        result.add(countJobs);
        result.add(maxProfit);
        return result;
    }

    // Helper class to store job data
    static class Job {
        int deadline, profit;
        Job(int deadline, int profit) {
            this.deadline = deadline;
            this.profit = profit;
        }
    }
}



//{ Driver Code Starts.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine().trim());

        while (t-- > 0) {
            String[] deadlineInput = sc.nextLine().trim().split("\\s+");
            int[] deadline =
                Arrays.stream(deadlineInput).mapToInt(Integer::parseInt).toArray();

            String[] profitInput = sc.nextLine().trim().split("\\s+");
            int[] profit =
                Arrays.stream(profitInput).mapToInt(Integer::parseInt).toArray();
            Solution obj = new Solution();
            ArrayList<Integer> result = obj.jobSequencing(deadline, profit);
            System.out.println(result.get(0) + " " + result.get(1));
            System.out.println("~");
        }

        sc.close();
    }
}
// } Driver Code Ends