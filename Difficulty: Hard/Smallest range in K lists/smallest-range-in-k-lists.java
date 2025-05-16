//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

public class DriverClass {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int arr[][] = new int[k][n];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < n; j++) arr[i][j] = sc.nextInt();
            }
            ArrayList<Integer> range = new Solution().findSmallestRange(arr);
            System.out.println(range.get(0) + " " + range.get(1));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends




class Solution {
    static class Element {
        int val, row, col;

        Element(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    public ArrayList<Integer> findSmallestRange(int[][] nums) {
        int k = nums.length;
        PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        int max = Integer.MIN_VALUE;

        // Step 1: Add first element of each list into the min-heap
        for (int i = 0; i < k; i++) {
            int val = nums[i][0];
            pq.add(new Element(val, i, 0));
            max = Math.max(max, val);
        }

        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;

        // Step 2: Process the heap
        while (pq.size() == k) {
            Element curr = pq.poll();
            int min = curr.val;

            // Update range if smaller
            if (max - min < rangeEnd - rangeStart) {
                rangeStart = min;
                rangeEnd = max;
            }

            // Move to next element in the same row
            if (curr.col + 1 < nums[curr.row].length) {
                int nextVal = nums[curr.row][curr.col + 1];
                pq.add(new Element(nextVal, curr.row, curr.col + 1));
                max = Math.max(max, nextVal);
            } else {
                break;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        res.add(rangeStart);
        res.add(rangeEnd);
        return res;
    }
}
