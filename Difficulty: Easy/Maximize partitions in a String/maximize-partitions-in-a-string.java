//{ Driver Code Starts
import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        while (tc-- > 0) {
            String s = sc.next();
            Solution obj = new Solution();
            int ans = obj.maxPartitions(s);
            System.out.println(ans);
            System.out.println("~");
        }
    }
}
// } Driver Code Ends



class Solution {
    public int maxPartitions(String s) {
        // Step 1: Store the last index of each character
        HashMap<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }

        // Step 2: Traverse and create partitions
        int partitions = 0;
        int maxRight = 0;
        
        for (int i = 0; i < s.length(); i++) {
            maxRight = Math.max(maxRight, lastIndex.get(s.charAt(i))); // Update right boundary
            
            // If we reach the right boundary, a partition ends here
            if (i == maxRight) {
                partitions++;
            }
        }
        
        return partitions;
    }

    
}
