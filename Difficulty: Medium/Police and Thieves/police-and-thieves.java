import java.util.ArrayList;
import java.util.List;
import java.lang.Math; // Math.abs is part of java.lang, so it's implicitly available.

class Solution {
    /**
     * Finds the maximum number of thieves that can be caught by police.
     * Each policeman can catch only one thief.
     * A policeman cannot catch a thief who is more than k units away from him.
     *
     * @param arr The character array representing police ('P') and thieves ('T').
     * @param k   The maximum distance a policeman can catch a thief.
     * @return The maximum number of thieves caught.
     */
    public int catchThieves(char[] arr, int k) {
        // Step 1: Separate the indices of policemen and thieves.
        // This is done to create two sorted lists of positions, which facilitates
        // the efficient two-pointer approach in the next step.
        List<Integer> policemen = new ArrayList<>();
        List<Integer> thieves = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'P') {
                policemen.add(i);
            } else { // It must be 'T'
                thieves.add(i);
            }
        }

        int caughtCount = 0; // Initialize the count of caught thieves
        int pPtr = 0;        // Pointer for the 'policemen' list
        int tPtr = 0;        // Pointer for the 'thieves' list

        // Step 2: Use the two-pointer technique to find optimal matches.
        // The loop continues as long as there are both policemen and thieves
        // available to be considered for a catch.
        while (pPtr < policemen.size() && tPtr < thieves.size()) {
            int pPos = policemen.get(pPtr); // Get the current policeman's index
            int tPos = thieves.get(tPtr);   // Get the current thief's index

            // Check if the current policeman and thief are within catching range 'k'.
            // Math.abs(pPos - tPos) calculates the absolute distance between them.
            if (Math.abs(pPos - tPos) <= k) {
                // If they are within range, they form a valid and optimal pair.
                // We increment the caught count.
                caughtCount++;
                // Both the policeman and the thief are now "used" or "caught",
                // so we move to the next available policeman and thief.
                pPtr++;
                tPtr++;
            } else if (pPos < tPos) {
                // If the policeman is to the left of the thief (pPos < tPos)
                // AND they are out of range (meaning tPos - pPos > k).
                // In this scenario, the current policeman is too far left to catch
                // the current thief. Since both `policemen` and `thieves` lists
                // are sorted by index, any subsequent thief will be even further
                // to the right. Therefore, this policeman cannot catch the current
                // thief or any future thieves. We must move to consider the next policeman.
                pPtr++;
            } else { // tPos < pPos
                // If the thief is to the left of the policeman (tPos < pPos)
                // AND they are out of range (meaning pPos - tPos > k).
                // In this scenario, the current thief is too far left to be caught
                // by the current policeman. Since the `policemen` list is sorted,
                // any subsequent policeman will be even further to the right.
                // Thus, this thief cannot be caught by the current policeman or
                // any future policeman. This thief is effectively "escaped"
                // from all current and future policemen. We move to consider the next thief.
                tPtr++;
            }
        }

        // Return the total number of thieves caught.
        return caughtCount;
    }
}