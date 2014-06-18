/*
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 */
/*
 * Maintain a linked list of next pointers, connecting an element to
 * its immediate succesor.(nextP)
 * after that traverse the list starting from universal source nodes(which are
 * not in the pointedTo set) to find out the largest list.
 */
package LongestConsecutiveSequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

	public int longestConsecutive(int[] num) {
		Map<Integer, Integer> elToIndex = new HashMap<Integer, Integer>();
		for (int i = 0; i < num.length; ++i) {
			elToIndex.put(num[i], i);
		}
		int n = num.length;
		int max = 0;
		int[] nextP = new int[n];
		Arrays.fill(nextP, -1);
		Set<Integer> pointedTo = new HashSet<Integer>();
		for (int i = 0; i < n; ++i) {
			int next = num[i] + 1;
			Integer numIndex = elToIndex.get(next);
			if (numIndex != null) {
				nextP[i] = numIndex;
				pointedTo.add(numIndex);
			}
		}
		for (int i = 0; i < n; ++i) {
			if (pointedTo.contains(i)) {
				continue;
			}
			int length = 1;
			int nextPointer = nextP[i];
			while (nextPointer != -1) {
				++length;
				nextPointer = nextP[nextPointer];
			}
			if (length > max) {
				max = length;
			}
		}
		return max;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.longestConsecutive(new int[] { 100, 4, 200, 1,
				3, 2 }));
		System.out.println(sol.longestConsecutive(new int[] { 1, 0, -1 }));
	}
}
