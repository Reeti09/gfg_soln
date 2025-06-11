import java.util.Stack; // Don't forget to import Stack

class Solution {

    // Inner class to represent a Ball with its color and radius
    private static class Ball {
        int color;
        int radius;

        Ball(int color, int radius) {
            this.color = color;
            this.radius = radius;
        }
    }

    /**
     * Removes consecutive balls with the same color and radius until no more such pairs exist.
     *
     * @param color  An array representing the colors of the balls.
     * @param radius An array representing the radii of the balls.
     * @return The number of balls remaining after all possible removals.
     */
    public int findLength(int[] color, int[] radius) {
        // We use a Stack to keep track of the balls that are not yet removed.
        Stack<Ball> stack = new Stack<>();

        // Iterate through each ball in the input arrays
        for (int i = 0; i < color.length; i++) {
            // Create a Ball object for the current ball
            Ball currentBall = new Ball(color[i], radius[i]);

            // Check if the stack is not empty AND
            // if the current ball's color and radius match the ball at the top of the stack
            if (!stack.isEmpty() && 
                stack.peek().color == currentBall.color && 
                stack.peek().radius == currentBall.radius) {
                
                // If they match, remove the top ball from the stack.
                // This simulates removing both the current ball and the matched top ball.
                stack.pop();
            } else {
                // If they don't match, or the stack is empty,
                // push the current ball onto the stack.
                stack.push(currentBall);
            }
        }

        // The number of remaining balls is simply the final size of the stack.
        return stack.size();
    }
}