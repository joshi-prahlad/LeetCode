/*
 * Given a string S,
 * find the longest palindromic substring in S. You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 */
package LongestPalindromicSubstring;

public class Solution {
	public String longestPalindrome1(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		int[][] result = new int[s.length()][s.length()];
		int beginIndex = -1, endIndex = -1;
		int maxLength = 0;
		for (int k = 0; k < s.length(); ++k) {
			for (int i = 0, j = 0; i < s.length() && j + k < s.length(); ++i, ++j) {
				if (i == j + k) {
					result[i][j + k] = 1;
				} else {
					if (s.charAt(i) == s.charAt(j + k)) {
						result[i][j + k] = 2 + result[i + 1][j + k - 1];
					} else {
						result[i][j + k] = result[i + 1][j + k - 1];
					}
				}
				if (maxLength < result[i][j + k]) {
					maxLength = result[i][j + k];
					beginIndex = i;
					endIndex = j + k;
				}
			}
		}
		// System.out.println(maxLength);
		return s.substring(beginIndex, endIndex + 1);
	}

	public String longestPalindrome(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		boolean[][] result = new boolean[s.length()][s.length()];
		int beginIndex = -1, endIndex = -1;
		int maxLength = 0;
		for (int k = 0; k < s.length(); ++k) {
			for (int i = 0, j = 0; i < s.length() && j + k < s.length(); ++i, ++j) {
				if (i == j + k) {
					result[i][j + k] = true;
				} else {
					if (s.charAt(i) == s.charAt(j + k)) {
						result[i][j + k] = true && (i + 1 >= j + k - 1 ? true
								: result[i + 1][j + k - 1]);
					} else {
						result[i][j + k] = false;
					}
				}
				if (result[i][j + k] && maxLength < j + k - i + 1) {
					maxLength = j + k - i + 1;
					beginIndex = i;
					endIndex = j + k;
				}
			}
		}
		// System.out.println(maxLength);
		return s.substring(beginIndex, endIndex + 1);
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.longestPalindrome("abcxcde"));
		System.out.println(sol.longestPalindrome("bb"));
	}
}
