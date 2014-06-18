/*
 * Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
 */
package PlusOne;

import java.util.Arrays;

public class Solution {
	public int[] plusOne(int[] digits) {
		int[] result = new int[digits.length + 1];
		boolean carry = true;
		for (int i = digits.length - 1, k = result.length - 1; i >= 0; --i, --k) {
			if (digits[i] == 9) {
				if (carry) {
					result[k] = 0;
				} else {
					result[k] = digits[i];
				}
			} else {
				if (carry) {
					result[k] = digits[i] + 1;
					carry = false;
				} else {
					result[k] = digits[i];
				}
			}
		}
		if (carry) {
			result[0] = 1;
			return result;
		} else {
			return Arrays.copyOfRange(result, 1, result.length);
		}
	}
}
