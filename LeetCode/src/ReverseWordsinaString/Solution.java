/*
 * Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

 */
package ReverseWordsinaString;

public class Solution {
	public String reverseWords(String s) {
		if (s == null || s.trim().length() < 2) {
			return s.trim();
		}
		char[] arr = s.trim().toCharArray();
		reverse(arr);
		boolean inWord = false;
		int begin = 0, end = -1;
		char[] rev = new char[arr.length];
		int j = 0;
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] == ' ') {
				if (inWord) {
					inWord = false;
					end = i - 1;
					if (end - begin + 1 > 0) {
						while (end >= begin) {
							rev[j++] = arr[end--];
						}
					}
				}
				rev[j++] = ' ';
				while (arr[i] == ' ') {
					++i;
				}
				--i;
				continue;
			} else {
				if (!inWord) {
					inWord = true;
					begin = i;
				}
			}
		}
		end = arr.length - 1;
		if (inWord) {
			while (end >= begin) {
				rev[j++] = arr[end--];
			}
		}
		return new String(rev);
	}

	void reverse(char[] arr) {
		for (int i = 0, j = arr.length - 1; i < j; ++i, --j) {
			char c = arr[i];
			arr[i] = arr[j];
			arr[j] = c;
		}
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.reverseWords("   a   b "));
	}
}
