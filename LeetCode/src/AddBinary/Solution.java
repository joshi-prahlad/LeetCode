package AddBinary;

/*
 * Given two binary strings, return their sum (also a binary string).

 For example,
 a = "11"
 b = "1"
 Return "100".
 */
public class Solution {
	public String addBinary(String a, String b) {
		if (a == null || a.isEmpty()) {
			return b;
		}
		if (b == null || b.isEmpty()) {
			return a;
		}
		if (a.length() < b.length()) {
			String c = a;
			a = b;
			b = c;
		}
		char[] result = new char[Math.max(a.length(), b.length()) + 1];
		boolean carry = false;
		int i = a.length() - 1, k = result.length - 1;
		for (int j = b.length() - 1; j >= 0; --i, --j, --k) {
			if (a.charAt(i) == '1' && b.charAt(j) == '1') {
				if (carry) {
					result[k] = '1';
				} else {
					result[k] = '0';
					carry = true;

				}
				continue;
			}
			if (a.charAt(i) == '0' && b.charAt(j) == '0') {
				result[k] = carry ? '1' : '0';
				carry = false;
				continue;
			}
			if (carry) {
				result[k] = '0';
			} else {
				result[k] = '1';
				carry = false;
			}
		}
		// System.out.println("re " + Arrays.toString(result));
		for (; i >= 0; --i, --k) {
			if (carry) {
				if (a.charAt(i) == '1') {
					result[k] = '0';
				} else {
					result[k] = '1';
					carry = false;
				}
			} else {
				result[k] = a.charAt(i);
			}
		}
		// System.out.println("re " + Arrays.toString(result));
		if (carry) {
			result[0] = '1';
			return new String(result);
		} else {
			return new String(result).substring(1);
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.addBinary("11", "1"));
	}
}
