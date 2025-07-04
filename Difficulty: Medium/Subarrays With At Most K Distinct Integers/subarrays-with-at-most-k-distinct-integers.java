import java.util.HashMap;
import java.util.Map;

// Changed to package-private to resolve the compilation error.
// A single .java file can only have one public class, and its name must match the file name.
class SubarraysWithAtMostKDistinct {

    /**
     * Calculates the number of subarrays with at most k distinct integers.
     * This method uses the sliding window technique.
     *
     * @param arr The input array of positive integers.
     * @param k The maximum allowed number of distinct integers in a subarray.
     * @return The total count of valid subarrays.
     */
    public int subarraysWithAtMostKDistinct(int[] arr, int k) {
        // If k is negative, no distinct elements are allowed, which is impossible for positive integers.
        if (k < 0) {
            return 0;
        }

        int count = 0; // Stores the total number of valid subarrays
        int left = 0;  // Left pointer of the sliding window
        // frequencyMap stores the count of each element within the current window
        // Using HashMap for frequency tracking as elements are positive integers.
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Iterate with the right pointer to expand the window
        for (int right = 0; right < arr.length; right++) {
            // Add the current element to the frequency map
            // If the element is new, put 1. Otherwise, increment its existing count.
            frequencyMap.put(arr[right], frequencyMap.getOrDefault(arr[right], 0) + 1);

            // Shrink the window from the left if the number of distinct elements
            // in the current window exceeds k
            while (frequencyMap.size() > k) {
                // Decrement the count of the element at the left pointer
                frequencyMap.put(arr[left], frequencyMap.get(arr[left]) - 1);

                // If its count becomes 0, remove it from the map
                // because it's no longer present in the current window
                if (frequencyMap.get(arr[left]) == 0) {
                    frequencyMap.remove(arr[left]);
                }
                // Move the left pointer to the right to shrink the window
                left++;
            }

            // At this point, the window arr[left...right] has at most k distinct elements.
            // All subarrays ending at 'right' and starting from 'left' up to 'right'
            // are valid. The number of such subarrays is (right - left + 1).
            count += (right - left + 1);
        }

        return count;
    }

    
}

// Class structure as provided by the user, with the method body filled in
class Solution {
    public int countAtMostK(int arr[], int k) {
        // If k is negative, no distinct elements are allowed, which is impossible for positive integers.
        if (k < 0) {
            return 0;
        }

        int count = 0; // Stores the total number of valid subarrays
        int left = 0;  // Left pointer of the sliding window
        // frequencyMap stores the count of each element within the current window
        // Using HashMap for frequency tracking as elements are positive integers.
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Iterate with the right pointer to expand the window
        for (int right = 0; right < arr.length; right++) {
            // Add the current element to the frequency map
            // If the element is new, put 1. Otherwise, increment its existing count.
            frequencyMap.put(arr[right], frequencyMap.getOrDefault(arr[right], 0) + 1);

            // Shrink the window from the left if the number of distinct elements
            // in the current window exceeds k
            while (frequencyMap.size() > k) {
                // Decrement the count of the element at the left pointer
                frequencyMap.put(arr[left], frequencyMap.get(arr[left]) - 1);

                // If its count becomes 0, remove it from the map
                // because it's no longer present in the current window
                if (frequencyMap.get(arr[left]) == 0) {
                    frequencyMap.remove(arr[left]);
                }
                // Move the left pointer to the right to shrink the window
                left++;
            }

            // At this point, the window arr[left...right] has at most k distinct elements.
            // All subarrays ending at 'right' and starting from 'left' up to 'right'
            // are valid. The number of such subarrays is (right - left + 1).
            count += (right - left + 1);
        }

        return count;
    }
}
