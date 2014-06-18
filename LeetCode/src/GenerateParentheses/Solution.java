/*
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"


 */

package GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	List<String> result = new ArrayList<String>();
	int used = 0;
	char current[];

	public List<String> generateParenthesis(int n) {
		current = new char[2 * n];
		helper(n, 0, 0);
		return result;
	}

	void helper(int n, int currentlyOpen, int charIndex) {
		if (charIndex > 2 * n - 1) {
			// current[charIndex] = ')';
			result.add(new String(current));
		}
		if (used < n) {
			current[charIndex] = '(';
			++used;
			helper(n, currentlyOpen + 1, charIndex + 1);
			--used;
			current[charIndex] = ')';
			if (currentlyOpen > 0) {
				helper(n, currentlyOpen - 1, charIndex + 1);
			}
		} else {
			if (currentlyOpen > 0) {
				current[charIndex] = ')';
				helper(n, currentlyOpen - 1, charIndex + 1);
			}
		}
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.generateParenthesis(4));
	}
}
