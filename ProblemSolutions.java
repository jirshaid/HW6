/******************************************************************
 *
 *   Jenine Irshaid / 002
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     */
    public static int lastBoulder(int[] boulders) {

        // If no boulders, nothing remains
        if (boulders == null || boulders.length == 0) {
            return 0;
        }

        // Use Java's PriorityQueue as a max-heap (largest element on top)
        java.util.PriorityQueue<Integer> pq =
                new java.util.PriorityQueue<>(java.util.Collections.reverseOrder());

        for (int w : boulders) {
            pq.offer(w);
        }

        // Repeatedly smash the two heaviest
        while (pq.size() > 1) {
            int y = pq.poll();   // heaviest
            int x = pq.poll();   // second heaviest

            if (y != x) {
                pq.offer(y - x);
            }
            // if y == x both are destroyed (we just don't add anything back)
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }


    /**
     * Method showDuplicates
     */
    public static ArrayList<String> showDuplicates(ArrayList<String> input) {

        Map<String, Integer> freq = new HashMap<>();

        for (String s : input) {
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        }

        ArrayList<String> result = new ArrayList<>();

        // Add only strings that appear more than once
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }

        // Sort ascending (lexicographically)
        Collections.sort(result);

        return result;  // sorted ascending order
    }


    /**
     * Finds pairs in the input array that add up to k.
     */
    public static ArrayList<String> pair(int[] input, int k) {

        // Frequency map of values
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : input) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        ArrayList<int[]> pairs = new ArrayList<>();

        for (int num : freq.keySet()) {
            int comp = k - num;

            if (!freq.containsKey(comp)) {
                continue;
            }

            // Only consider each unordered pair once
            if (num > comp) {
                continue;
            }

            // If num == comp, need at least 2 occurrences
            if (num == comp && freq.get(num) < 2) {
                continue;
            }

            pairs.add(new int[]{num, comp});
        }

        // Sort pairs by first, then second element
        pairs.sort((p1, p2) -> {
            if (p1[0] != p2[0]) {
                return Integer.compare(p1[0], p2[0]);
            }
            return Integer.compare(p1[1], p2[1]);
        });

        // Convert to "(a, b)" strings
        ArrayList<String> result = new ArrayList<>();
        for (int[] p : pairs) {
            result.add("(" + p[0] + ", " + p[1] + ")");
        }

        return result;  // sorted as required
    }
}
