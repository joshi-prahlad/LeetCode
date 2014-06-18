/*
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 */

package WordBreak;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	public boolean wordBreak(String s, Set<String> dict) {
		if (s == null || s.isEmpty() || dict == null | dict.isEmpty()) {
			return false;
		}
		boolean[][] result = new boolean[s.length()][s.length()];
		for (int k = 0; k < s.length(); ++k) {
			for (int i = 0, j = 0; i < s.length() && j + k < s.length(); ++i, ++j) {
				if (i == j + k) {
					result[i][i] = dict.contains(s.substring(i, i + 1));
				} else {
					if (dict.contains(s.substring(i, j + k + 1))) {
						result[i][j + k] = true;
					} else {
						for (int l = i; l < j + k; ++l) {
							if (result[i][l] && result[l + 1][j + k]) {
								result[i][j + k] = true;
							}
						}
					}
				}
			}
		}
		// for (boolean[] arr : result) {
		// System.out.println(Arrays.toString(arr));
		// }
		return result[0][result.length - 1];
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		Set<String> set = new HashSet<String>();
		set.add("leet");
		set.add("code");
		System.out.println(sol.wordBreak("leetcode", set));
		// System.out.println(sol.helper("bb"));
	}
}
