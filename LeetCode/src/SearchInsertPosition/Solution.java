/*
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
 */
package SearchInsertPosition;

public class Solution {
	public int searchInsert(int[] A, int target) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int low = 0, high = A.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (A[mid] == target) {
				return mid;
			}
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return high + 1;

	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.searchInsert(new int[] { 1, 3 }, 0));
		System.out.println(sol.searchInsert(new int[] { 1, 3, 5, 6 }, 2));
		System.out.println(sol.searchInsert(new int[] { 1, 3, 5, 6 }, 5));
		System.out.println(sol.searchInsert(new int[] { 1, 3, 5, 6 }, 7));
	}
}
