/*
 * Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

package Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Solution {

	public List<List<Integer>> subsets(int[] s) {
		Arrays.sort(s);
		List<List<Integer>> ll = new ArrayList<List<Integer>>();
		ll.add(new ArrayList<Integer>());
		for (int el : s) {
			ListIterator<List<Integer>> it = ll.listIterator();
			while (it.hasNext()) {
				List<Integer> newL = new ArrayList<Integer>();
				List<Integer> l = it.next();
				newL.addAll(l);
				newL.add(el);
				it.add(newL);
			}
		}
		return ll;
		// return helper(s, 0);
	}

	List<List<Integer>> helper(int[] s, int index) {
		if (s == null || index >= s.length) {
			List<Integer> l = new ArrayList<Integer>();
			List<List<Integer>> ll = new ArrayList<List<Integer>>();
			ll.add(l);
			return ll;
		}
		List<List<Integer>> sub = helper(s, index + 1);
		List<List<Integer>> ll = new ArrayList<List<Integer>>();
		for (List<Integer> el : sub) {
			ll.add(el);
			el.add(s[index]);
			ll.add(el);
		}
		return ll;
	}
}
