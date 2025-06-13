import java.util.Arrays; // For finding max element, though a loop would also suffice

class Solution {

    /**
     * Helper function to calculate the total hours Koko needs to eat all bananas
     * at a given eating speed 'speed'.
     *
     * @param speed The eating speed (bananas per hour).
     * @param piles An array representing the number of bananas in each pile.
     * @param maxHours The maximum hours allowed.
     * @return true if Koko can finish all bananas within 'maxHours' at 'speed', false otherwise.
     */
    private boolean canFinish(int speed, int[] piles, int maxHours) {
        long totalHours = 0; // Use long to prevent potential overflow for very large total hours

        for (int pile : piles) {
            // Calculate hours for the current pile: ceil(pile / speed)
            // Using integer division: (a + b - 1) / b for ceil(a / b)
            totalHours += (long) (pile + speed - 1) / speed;

            // Optimization: If total hours already exceed 'maxHours', no need to check further piles.
            if (totalHours > maxHours) {
                return false;
            }
        }
        return totalHours <= maxHours;
    }

    /**
     * Finds the minimum integer eating speed 'k_speed' such that Koko can eat all
     * bananas in 'arr' within 'total_h' hours.
     *
     * This problem is solved using binary search on the possible values of 'k_speed'.
     *
     * Time Complexity: O(N * log(Max_Pile_Size))
     * - log(Max_Pile_Size) for the binary search iterations.
     * - N for each call to `canFinish` (iterating through 'arr').
     * Space Complexity: O(1) (excluding input storage).
     *
     * @param arr An array representing the number of bananas in each pile.
     * @param total_h The maximum hours Koko has.
     * @return The minimum eating speed 'k_speed'.
     */
    public int kokoEat(int[] arr, int total_h) { // Renamed from minEatingSpeed to kokoEat, and h to total_h
        // Step 1: Determine the search space for 'k_speed'.
        // Minimum possible 'k_speed' is 1 (Koko eats at least 1 banana per hour).
        int low = 1;
        
        // Maximum possible 'k_speed' is the size of the largest pile.
        // In the worst case, Koko eats the largest pile in 1 hour, and then deals with others.
        int high = 0;
        for (int pile : arr) {
            high = Math.max(high, pile);
        }

        int minK = high; // Initialize result with the highest possible 'k_speed'

        // Step 2: Perform binary search for the minimum 'k_speed'.
        while (low <= high) {
            int mid = low + (high - low) / 2; // Calculate midpoint 'k_speed'

            // If Koko can finish all bananas within 'total_h' hours at speed 'mid'
            if (canFinish(mid, arr, total_h)) { // Pass arr and total_h
                minK = mid;     // 'mid' is a possible answer; try to find a smaller 'k_speed'.
                high = mid - 1; // Search in the lower half (smaller speeds).
            } else {
                // Koko cannot finish at speed 'mid'; she needs to eat faster.
                low = mid + 1; // Search in the upper half (faster speeds).
            }
        }

        return minK; // Return the minimum 'k_speed' found.
    }

    
}
