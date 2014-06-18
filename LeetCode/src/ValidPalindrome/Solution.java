package ValidPalindrome;

/*
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome.
 */
public class Solution {
	public boolean isPalindrome(String s) {
		s = s.toLowerCase();
		char charArr[] = s.toCharArray();
		for (int i = 0, j = charArr.length - 1; i < j;) {
			if (Character.isLetterOrDigit(charArr[i])
					&& Character.isLetterOrDigit(charArr[j])) {
				if (charArr[i] != charArr[j]) {
					return false;
				}
				++i;
				--j;
				continue;
			}
			if (!Character.isLetterOrDigit(charArr[i])) {
				++i;
			}
			if (!Character.isLetterOrDigit(charArr[j])) {
				--j;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(Character.isAlphabetic('1'));

		System.out.println(sol.isPalindrome("1a2"));
	}
}
