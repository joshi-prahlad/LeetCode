/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */

/*
 * If there are two consecutive zeros return zero.
 * if ith character is 0 then s(i-1,i) must be a valid string from 1-26 otherwise number of ways is zero.
 * if i-1 and ith character is a valid string from 1-26 then
 * ways(s(1...i) = ways(1...i-1)+ ways(1...i-2)
 */
package DecodeWays;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	boolean isInit = false;
	Set<String> basic = new HashSet<String>();

	public int numDecodings(String s) {
		if (s.isEmpty()) {
			return 0;
		}
		int ways[] = new int[s.length()];
		if (s.charAt(0) > '0' && s.charAt(0) <= '9') {
			ways[0] = 1;
		}
		if (s.length() == 1) {
			return ways[0];
		}
		boolean isB = isBasic(s.substring(0, 2));
		if (isB) {
			if (s.charAt(1) != '0' && s.charAt(0) != '0') {
				ways[1] = 2;
			} else {
				ways[1] = 1;
			}
		} else {
			ways[1] = s.charAt(1) == '0' ? 0 : ways[0];
		}
		for (int i = 2; i < s.length(); ++i) {
			isB = isBasic(s.substring(i - 1, i + 1));
			if (s.charAt(i) == '0') {
				if (s.charAt(i - 1) == '0') {
					return 0;
				}
				if (isB) {
					ways[i] = ways[i - 2];
				} else {
					ways[i] = 0;
				}
			} else {
				if (s.charAt(i - 1) == '0') {
					ways[i] = ways[i - 1];
				} else {
					if (isB) {
						ways[i] = ways[i - 1] + ways[i - 2];
						if (ways[i] < 0) {
							ways[i] = 0;
						}
					} else {
						ways[i] = ways[i - 1];
					}
				}
			}
		}
		// System.out.println(Arrays.toString(ways));
		return ways[ways.length - 1];
	}

	boolean isBasic(String arg) {
		if (!isInit) {
			for (int i = 1; i < 27; ++i) {
				basic.add("" + i);
			}
		}
		return basic.contains(arg);
	}

	public static void main(String args[]) {
		Solution sol = new Solution();

		System.out.println(sol.numDecodings("01"));
		System.out.println(sol.numDecodings("10"));
		System.out.println(sol.numDecodings("100"));
		System.out.println(sol.numDecodings("111"));
		System.out.println(sol.numDecodings("012"));
		System.out.println(sol.numDecodings("121"));
		System.out.println(sol.numDecodings("1212"));
		System.out.println(sol.numDecodings("102"));
	}
}
