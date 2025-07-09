import java.util.Stack; // Don't forget to import Stack

class Solution {
    public int sumSubMins(int[] arr) {
        // The problem states the total sum will fit within a 32-bit unsigned integer.
        // However, intermediate products (like arr[i] * countLeft * countRight) can
        // exceed the capacity of a standard Java 'long' if array elements and N are large.
        // Therefore, applying a modulo operation (commonly 10^9 + 7) at each multiplication
        // and addition step is crucial to prevent overflow and ensure correctness.
        // The final result, after modulo, will safely fit into an 'int'.
        long MOD = 1_000_000_007; 
        
        int n = arr.length; // Get the number of elements in the array

        // left[i]: Stores the index of the Nearest Smaller Element (NSE) to the left of arr[i].
        // If no such element exists, left[i] will be -1.
        int[] left = new int[n];
        
        // right[i]: Stores the index of the Nearest Smaller or Equal Element (NSEE) to the Right of arr[i].
        // If no such element exists, right[i] will be 'n' (array length).
        // Using NSEE on the right side helps handle duplicate minimums correctly,
        // ensuring each instance of a minimum value is counted uniquely.
        int[] right = new int[n];

        // A monotonic stack is used to efficiently find NSE/NSEE for all elements.
        Stack<Integer> stack = new Stack<>();

        // --- Step 1: Calculate NSL (Nearest Smaller Element to the Left) for each element ---
        // Iterate from left to right.
        for (int i = 0; i < n; i++) {
            // While the stack is not empty AND the element at the top of the stack is 
            // greater than or equal to the current element arr[i], pop from the stack.
            // This maintains a stack where elements are strictly increasing from bottom to top.
            // Popping elements >= arr[i] ensures that the peeked element (if any) is strictly smaller.
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            
            // If the stack becomes empty, it means there is no smaller element to the left of arr[i].
            // So, its left boundary for minimum calculation is considered -1.
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            
            // Push the current element's index onto the stack.
            stack.push(i); 
        }

        // Clear the stack for the next pass (calculating NSEE).
        stack.clear(); 

        // --- Step 2: Calculate NSEE (Nearest Smaller or Equal Element to the Right) for each element ---
        // Iterate from right to left.
        for (int i = n - 1; i >= 0; i--) {
            // While the stack is not empty AND the element at the top of the stack is 
            // strictly greater than the current element arr[i], pop from the stack.
            // This maintains a stack where elements are non-decreasing (increasing or equal).
            // By popping strictly greater elements, we ensure the peeked element (if any) is smaller or equal to arr[i].
            // This subtle difference ('>' vs '>=') is vital for handling duplicates correctly.
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            
            // If the stack becomes empty, it means there is no smaller or equal element to the right of arr[i].
            // So, its right boundary for minimum calculation is considered 'n'.
            right[i] = stack.isEmpty() ? n : stack.peek();
            
            // Push the current element's index onto the stack.
            stack.push(i);
        }

        // --- Step 3: Calculate the total sum of minimums ---
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            // 'countLeft': Represents the number of valid starting positions for subarrays 
            // where arr[i] is the minimum, considering elements to its left.
            // These positions range from (left[i] + 1) up to 'i' (inclusive of 'i').
            long countLeft = (long)i - left[i];

            // 'countRight': Represents the number of valid ending positions for subarrays 
            // where arr[i] is the minimum, considering elements to its right.
            // These positions range from 'i' up to (right[i] - 1) (inclusive of 'i').
            long countRight = (long)right[i] - i;

            // The contribution of arr[i] to the total sum is:
            // arr[i] * (number of ways to choose a left boundary) * (number of ways to choose a right boundary)
            // We cast arr[i] to long before multiplication to prevent potential overflow.
            long contribution = ((long)arr[i] * countLeft) % MOD;
            contribution = (contribution * countRight) % MOD;

            // Add the current element's contribution to the total sum, applying modulo.
            totalSum = (totalSum + contribution) % MOD;
        }

        // The problem guarantees the total sum fits within a 32-bit unsigned integer,
        // and we have applied modulo operations. Therefore, casting to 'int' is safe here.
        return (int) totalSum;
    }
}