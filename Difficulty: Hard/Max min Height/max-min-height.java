import java.util.Arrays; // Not strictly needed for this solution, but often useful for array operations

class Solution {

    // Helper function to check if it's possible to achieve 'targetHeight'
    // as the minimum height for all flowers using at most 'k' watering days.
    private boolean check(int targetHeight, int[] arr, int k, int w, int n) {
        // waterAppliedAtStartOfSegment[i] keeps track of the additional height
        // that starts being applied from index 'i' due to watering.
        // We use 'long' for sums to prevent potential overflow, especially if 'k' is large.
        long[] waterAppliedAtStartOfSegment = new long[n + 1]; 
        long totalDaysSpent = 0;
        
        // currentEffectiveWater accumulates the total extra height a flower receives
        // from all watering segments that cover its position.
        long currentEffectiveWater = 0; 

        for (int i = 0; i < n; i++) {
            // Apply lazy propagation:
            // Add water that started at this index 'i'.
            // (If a watering operation started at index `j < i` and continued past `i`,
            // its effect is already included in `currentEffectiveWater` from previous iterations.)
            currentEffectiveWater += waterAppliedAtStartOfSegment[i];
            
            // Calculate the current actual height of the flower at index 'i'.
            // This is its original height plus any extra height gained from watering.
            long currentFlowerHeight = (long)arr[i] + currentEffectiveWater;

            // If the current flower's height is less than our 'targetHeight',
            // we must water it to bring it up to at least 'targetHeight'.
            if (currentFlowerHeight < targetHeight) {
                long neededWater = targetHeight - currentFlowerHeight;
                totalDaysSpent += neededWater; // Each unit of 'neededWater' here implies one day of watering this segment.

                // If we've already used more than 'k' days, then this 'targetHeight' is not achievable.
                if (totalDaysSpent > k) {
                    return false;
                }

                // This 'neededWater' amount is added to the current flower
                // and will also affect the next 'w-1' flowers (total 'w' flowers).
                currentEffectiveWater += neededWater; 
                
                // To stop the effect of this 'neededWater' after 'w' flowers,
                // we mark a negative entry at `i + w`. When the loop reaches `i + w`,
                // this negative value will effectively subtract the `neededWater` from `currentEffectiveWater`.
                if (i + w < n) { // Ensure i+w is a valid index to modify
                    waterAppliedAtStartOfSegment[i + w] -= neededWater;
                }
            }
        }
        // If the loop completes, it means all flowers can be brought to 'targetHeight'
        // or more, using at most 'k' days.
        return true; 
    }

    public int maxMinHeight(int[] arr, int k, int w) {
        int n = arr.length;

        // Determine the search range for our binary search.
        // The answer (maximum possible minimum height) will lie within this range.
        int minInitialHeight = Integer.MAX_VALUE;
        int maxInitialHeight = 0;

        for (int height : arr) {
            minInitialHeight = Math.min(minInitialHeight, height);
            maxInitialHeight = Math.max(maxInitialHeight, height);
        }

        // 'low' represents the smallest possible minimum height we might achieve.
        // It's at least the current minimum height.
        long low = minInitialHeight; 
        
        // 'high' represents the largest possible minimum height.
        // In the extreme case, if we water one flower 'k' times, its height could be maxInitialHeight + k.
        long high = maxInitialHeight + k; 
        
        // 'ans' will store the best (largest) minimum height we find that is achievable.
        long ans = minInitialHeight; 

        // Perform binary search on the possible minimum heights.
        while (low <= high) {
            long mid = low + (high - low) / 2; // Calculate mid to prevent potential integer overflow if low+high is very large

            // Call the 'check' function to see if 'mid' as a minimum height is achievable.
            if (check((int) mid, arr, k, w, n)) {
                // If 'mid' is achievable, it means we might be able to do even better.
                // Store 'mid' as a potential answer and try for a higher minimum height.
                ans = mid;
                low = mid + 1;
            } else {
                // If 'mid' is not achievable, it means 'mid' is too high.
                // We need to try for a lower minimum height.
                high = mid - 1;
            }
        }
        
        // The loop terminates when 'low' > 'high', and 'ans' holds the largest achievable minimum height.
        return (int) ans; 
    }
}