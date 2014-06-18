/*
 * Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 */
package SearchforARange;

public class Solution {
	public int[] searchRange(int[] A, int target) {
		if (A == null) {
			return new int[] { -1, -1 };
		}
		int[] result = new int[2];
		result[0] = getFirstIndex(A, target);
		result[1] = getLastIndex(A, target);
		return result;
	}

	int getFirstIndex(int[] a, int target) {
		int low = 0, high = a.length - 1;
		int minIndex = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (a[mid] == target) {
				if (mid == low || a[mid - 1] != target) {
					return mid;
				} else {
					minIndex = mid;
					high = mid - 1;
					continue;
				}
			}
			if (a[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return minIndex;
	}

	int getLastIndex(int[] a, int target) {
		int low = 0, high = a.length - 1;
		int maxIndex = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (a[mid] == target) {
				if (mid == high || a[mid + 1] != target) {
					return mid;
				} else {
					maxIndex = mid;
					low = mid + 1;
					continue;
				}
			}
			if (a[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return maxIndex;
	}
}
