/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 */
package SearchInRotatedSortedArray;

/*
 * The idea is either A[low..mid] or A[mid+1..high]
 * would be sorted in ascending order.
 * So find out mid if A[mid]==target return mid.
 * If A[low] < A[high] we are in sorted half so
 * modify low and high accordingly and continue.
 * If not then find out the sorted half and check whether
 * the target lies in it if not serach in the other half.
 */
public class Solution {
	public int search(int[] A, int target) {
		int low = 0, high = A.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (A[mid] == target) {
				return mid;
			}
			if (A[low] < A[high]) {

				if (target < A[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				if (A[mid] < A[high]) {
					if (target > A[mid] && target <= A[high]) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				} else {
					if (target < A[mid] && target >= A[low]) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				}
			}
		}
		return -1;
	}
}
