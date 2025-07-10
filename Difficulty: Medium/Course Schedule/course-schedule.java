import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    /**
     * Finds one valid order in which to complete all tasks, given their prerequisites.
     * If no such order exists (due to a circular dependency), returns an empty list.
     *
     * This method implements Kahn's Algorithm (BFS-based Topological Sort).
     *
     * @param n The total number of tasks (labeled from 0 to n-1).
     * @param prerequisites A 2D array where each [ai, bi] means task 'ai' requires 'bi' to be completed.
     * @return An ArrayList of Integers representing a valid task order, or an empty list if no order exists.
     */
    public static ArrayList<Integer> findOrder(int n, int[][] prerequisites) {
        // Step 1: Initialize Adjacency List and In-Degree Array

        // 'adj' will represent the directed graph.
        // adj.get(u) will contain a list of all tasks 'v' that require 'u' as a prerequisite (u -> v).
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // 'inDegree[i]' will store the number of prerequisites that task 'i' has.
        // This is the count of incoming edges to node 'i'.
        int[] inDegree = new int[n]; // CORRECT: Declared once outside the loop

        // Populate the adjacency list and in-degree array based on the prerequisites.
        // This loop should run AFTER 'adj' is fully initialized and 'inDegree' is declared.
        for (int[] pre : prerequisites) {
            int taskA = pre[0]; // The task that has a prerequisite
            int taskB = pre[1]; // The prerequisite task that must be completed before taskA

            adj.get(taskB).add(taskA); // Add a directed edge from taskB to taskA
            inDegree[taskA]++;          // Increment the in-degree of taskA
        }

        // Step 2: Initialize a Queue for BFS

        // The queue will store tasks that currently have no outstanding prerequisites (in-degree is 0).
        Queue<Integer> q = new LinkedList<>();

        // Add all tasks that initially have an in-degree of 0 to the queue.
        // This loop should run AFTER 'inDegree' is fully populated.
        for (int i = 0; i < n; i++) { // Correct loop variable 'i'
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        // This list will store the final topological order of tasks.
        ArrayList<Integer> resultOrder = new ArrayList<>(); // CORRECT: Declared once at the method level

        // 'tasksProcessed' counts how many tasks we have successfully "completed" so far.
        int tasksProcessed = 0; // CORRECT: Declared once at the method level

        // Step 3: Process Tasks using BFS and build the topological order

        while (!q.isEmpty()) {
            int current = q.poll(); // Get a task that can now be completed
            
            resultOrder.add(current); // Add this task to our result order
            tasksProcessed++;         // Increment the count of processed tasks

            // For all tasks that depend on 'current' (i.e., its neighbors in the graph):
            for (int dependent : adj.get(current)) {
                inDegree[dependent]--; // Decrement the in-degree of the dependent task

                // If a dependent task's in-degree becomes 0, it means all its prerequisites are now met.
                // So, it can be added to the queue to be processed next.
                if (inDegree[dependent] == 0) {
                    q.offer(dependent);
                }
            }
        }

        // Step 4: Check for Cycles and Return the Result

        // If 'tasksProcessed' equals 'n', it means we were able to process all 'n' tasks.
        // This implies there are no circular dependencies (no cycles) in the graph.
        // In this case, 'resultOrder' contains a valid topological sort.
        if (tasksProcessed == n) {
            return resultOrder; // CORRECT: Returning ArrayList<Integer>
        } else {
            // If 'tasksProcessed' is less than 'n', it means some tasks could not be processed.
            // This happens when there's a cycle in the graph, making it impossible to complete all tasks.
            // In this case, a valid order is impossible, so return an empty list.
            return new ArrayList<>(); 
        }
    }
}