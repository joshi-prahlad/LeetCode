package ScrambleString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Solution {
	public boolean isScramble(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		if (s1.equals(s2)) {
			return true;
		}
		if (s1.length() == 1) {
			return false;
		}
		if (!match(s1, 0, s1.length() - 1, s2, 0, s2.length() - 1)) {
			return false;
		}
		Map<Character, Integer> charToPosS2 = new HashMap<Character, Integer>();
		Map<Character, Integer> charToPosS1 = new HashMap<Character, Integer>();
		for (int i = 0; i < s2.length(); ++i) {
			charToPosS2.put(s2.charAt(i), i);
			charToPosS1.put(s1.charAt(i), i);
		}
		for (int i = 0; i < s1.length(); ++i) {
			int s2Index = charToPosS2.get(s1.charAt(i));
			if (s2Index < i) {
				if (!match(s1, s2Index, i, s2, s2Index,
						charToPosS2.get(s1.charAt(s2Index)))) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean match(String a, int l1, int h1, String b, int l2, int h2) {
		if (h1 - l1 != h2 - l2) {
			return false;
		}
		Set<Character> chars1 = new HashSet<Character>();
		Set<Character> chars2 = new HashSet<Character>();
		for (int i = l1; i <= h1; ++i) {
			chars1.add(a.charAt(i));
		}
		for (int i = l2; i <= h2; ++i) {
			chars2.add(b.charAt(i));
		}
		if (chars1.size() != chars2.size()) {
			return false;
		}
		Iterator it = chars1.iterator();
		while (it.hasNext()) {
			if (!chars2.contains(it.next())) {
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println(s.isScramble("ab", "aa"));
	}
}
