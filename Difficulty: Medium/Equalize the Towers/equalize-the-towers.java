
class Solution {

    // Helper class to store height and its corresponding cost.
    // Implements Comparable to allow sorting by height.
    static class Tower implements Comparable<Tower> {
        int height;
        int cost;

        Tower(int height, int cost) {
            this.height = height;
            this.cost = cost;
        }

        @Override
        public int compareTo(Tower other) {
            // Sort towers primarily by height in ascending order.
            return Integer.compare(this.height, other.height);
        }
    }

    /**
     * Calculates the minimum cost to equalize the heights of all towers.
     *
     * @param heights An array of integers representing the initial heights of the towers.
     * @param cost    An array of integers representing the cost per unit of modification for each tower.
     * @return The minimum total cost required to make all towers the same height.
     */
    public long minCost(int[] heights, int[] cost) { // Changed return type to long as costs can be very large
        int N = heights.length;

        // Handle edge case: no towers
        if (N == 0) {
            return 0;
        }

        // Step 1: Combine heights and costs into Tower objects for easier sorting.
        List<Tower> towers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            towers.add(new Tower(heights[i], cost[i]));
        }

        // Step 2: Sort the towers based on their heights.
        Collections.sort(towers);

        // Step 3: Calculate prefix sums of costs.
        // prefixCostSum[i] stores the sum of costs from towers[0] to towers[i] in the sorted list.
        long[] prefixCostSum = new long[N];
        prefixCostSum[0] = towers.get(0).cost;
        for (int i = 1; i < N; i++) {
            prefixCostSum[i] = prefixCostSum[i - 1] + towers.get(i).cost;
        }
        
        // Calculate the total sum of all costs.
        long totalCostSum = prefixCostSum[N - 1];

        long minTotalCost = Long.MAX_VALUE;
        long currentTotalCost = 0;

        // Step 4: Calculate the initial cost when the target height is the smallest height (h_0).
        // This is our starting point for the dynamic calculation.
        for (int i = 0; i < N; i++) {
            currentTotalCost += (long) Math.abs(towers.get(i).height - towers.get(0).height) * towers.get(i).cost;
        }
        minTotalCost = currentTotalCost;

        // Step 5: Iterate through the sorted towers (from the second one onwards)
        // and efficiently update the cost based on the change in target height.
        for (int j = 1; j < N; j++) {
            // If the current tower has the same height as the previous one,
            // the 'slope' of the cost function doesn't change, so we can skip this iteration
            // as the deltaH would be 0, and currentTotalCost won't change.
            if (towers.get(j).height == towers.get(j - 1).height) {
                continue; 
            }

            // Calculate the difference in height from the previous candidate to the current.
            long deltaH = towers.get(j).height - towers.get(j - 1).height;
            
            // sumCostLeft: Sum of costs for all towers whose heights are less than or equal to
            // the previous target height (towers.get(j-1).height).
            // When H increases by deltaH, the cost for these towers increases.
            long sumCostLeft = prefixCostSum[j - 1]; 

            // sumCostRight: Sum of costs for all towers whose heights are greater than or equal to
            // the current target height (towers.get(j).height).
            // When H increases by deltaH, the cost for these towers decreases.
            long sumCostRight = totalCostSum - sumCostLeft;

            // Update currentTotalCost using the formula:
            // Cost(H_new) = Cost(H_old) + (Sum of costs of towers to the left) * deltaH
            //                            - (Sum of costs of towers to the right) * deltaH
            currentTotalCost += (sumCostLeft - sumCostRight) * deltaH;
            
            // Update the minimum total cost found so far.
            minTotalCost = Math.min(minTotalCost, currentTotalCost);
        }

        // Return the overall minimum cost.
        return minTotalCost;
    }
}
