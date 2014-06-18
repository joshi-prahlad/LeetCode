/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */

package MaximumSubarray;

public class Solution {

	public int maxSubArray(int[] A) {
		if (A.length < 1) {
			return 0;
		}
		int currentSum = 0, maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < A.length; ++i) {
			int newSum = currentSum + A[i];
			if (newSum < 0) {
				currentSum = 0;
			} else {
				currentSum = newSum;
			}
			if (maxSum < newSum) {
				maxSum = newSum;
			}
		}
		return maxSum;
	}
}
