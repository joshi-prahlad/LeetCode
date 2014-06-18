/*
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
 */
package InterleavingString;

import java.util.Arrays;

/*
 * Dynamic programming
 * Have a matrix of size n+1,m+1
 * which stores whether s3(1...i+j) is an interleaving of s1(1..i),s2(1...j) or not
 * if(s1[i]==s2[j]==s3[k])
 * then solution is either s1(1..i-1)s2(1...j)s3(1...k-1) || s1(1..i)s2(1...j-1)s3(1...k-1)
 * if(s1[i]==s3[k])
 * then solution is  s1(1..i-1)s2(1...j)s3(1...k-1)
 * if(s1[j]==s3[k])
 *  s1(1..i)s2(1...j-1)s3(1...k-1)
 */
public class Solution {
	public boolean isInterleave(String s1, String s2, String s3) {
		s1 = s1.trim();
		s2 = s2.trim();
		s3 = s3.trim();
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		if ((s1 + s2).equals(s3)) {
			return true;
		}
		if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
			if ((s1 + s2).equals(s3)) {
				return true;
			}
			return false;
		}
		boolean matrix[][] = new boolean[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[0].length; ++j) {
				int k = i + j;
				if (k == 0) {
					matrix[i][j] = true;
					continue;
				}
				if (i == 0) {
					if (s2.charAt(j - 1) == s3.charAt(k - 1)) {
						matrix[0][j] = matrix[0][j - 1];
					} else {
						matrix[0][j] = false;
					}
					continue;
				}
				if (j == 0) {
					if (s1.charAt(i - 1) == s3.charAt(k - 1)) {
						matrix[i][0] = matrix[i - 1][0];
					} else {
						matrix[i][0] = false;
					}
					continue;
				}
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					if (s1.charAt(i - 1) == s3.charAt(k - 1)) {
						matrix[i][j] = matrix[i - 1][j] || matrix[i][j - 1];
					} else {
						matrix[i][j] = false;
					}
					continue;
				}
				if (s1.charAt(i - 1) == s3.charAt(k - 1)) {
					matrix[i][j] = matrix[i - 1][j];
					continue;
				}
				if (s2.charAt(j - 1) == s3.charAt(k - 1)) {
					matrix[i][j] = matrix[i][j - 1];
					continue;
				}
				matrix[i][j] = false;
			}
		}
		for (boolean[] arr : matrix) {
			System.out.println(Arrays.toString(arr));
		}
		return matrix[matrix.length - 1][matrix[0].length - 1];
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		// System.out.println(sol.isInterleave("aabccabc", "dbbabc",
		// "aabdbbccababcc"));
		System.out.println(sol.isInterleave("aa", "ab", "abaa"));
		// System.out
		// .println(sol
		// .isInterleave(
		// "baababbabbababbaaababbbbbbbbbbbaabaabaaaabaaabbaaabaaaababaabaaabaabbbbaabbaabaabbbbabbbababbaaaabab",
		// "aababaaabbbababababaabbbababaababbababbbbabbbbbababbbabaaaaabaaabbabbaaabbababbaaaababaababbbbabbbbb",
		// "babbabbabbababbaaababbbbaababbaabbbbabbbbbaaabbabaababaabaaabaabbbaaaabbabbaaaaabbabbaabaaaabbbbababbbababbabaabababbababaaaaaabbababaaabbaabbbbaaaaabbbaaabbbabbbbaaabaababbaabababbbbababbaaabbbabbbab"));
	}
}
