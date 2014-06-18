/*
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
 */
package ThreeSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Pair {
	public final int a;
	public final int b;

	Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Pair) {
			Pair p = (Pair) o;
			return p.a == this.a && p.b == this.b;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}
}

public class Solution {

	public List<List<Integer>> threeSum(int[] num) {
		if (num == null || num.length == 0) {
			return new ArrayList<List<Integer>>();
		}
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		Map<Pair, Integer> pairToSum = new HashMap<Pair, Integer>();
		Map<Integer, Integer> valueToIndex = new HashMap<Integer, Integer>();
		for (int i = 0; i < num.length - 1; ++i) {
			for (int j = i + 1; j < num.length; ++j) {
				Pair p = new Pair(i, j);
				pairToSum.put(p, num[i] + num[j]);
			}
			valueToIndex.put(num[i], i);
		}
		valueToIndex.put(num[num.length - 1], num.length - 1);

		for (Entry<Pair, Integer> e : pairToSum.entrySet()) {
			int val = e.getValue();
			Integer index = null;
			if (val == 0) {
				index = valueToIndex.get(0);
			} else {
				index = valueToIndex.get(-val);
			}
			if (index != null && index != e.getKey().a && index != e.getKey().b) {
				List<Integer> temp = new ArrayList<Integer>();
				temp.add(num[index]);
				temp.add(num[e.getKey().a]);
				temp.add(num[e.getKey().b]);
				Collections.sort(temp);
				result.add(temp);

			}
		}
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.addAll(result);
		return list;
	}
}
