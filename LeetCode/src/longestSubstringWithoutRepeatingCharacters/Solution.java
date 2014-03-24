package longestSubstringWithoutRepeatingCharacters;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Solution {
	public int lengthOfLongestSubstring(String s) {

		int maxLength = 0;
		LinkedHashMap<Character, Integer> uniqueChars = new LinkedHashMap<>();
		for (int i = 0; i < s.length(); ++i) {
			if (!uniqueChars.containsKey(s.charAt(i))) {
				uniqueChars.put(s.charAt(i), i);
			} else {
				if (uniqueChars.size() > maxLength) {
					maxLength = uniqueChars.size();
				}
				Integer index = uniqueChars.get(s.charAt(i));
				LinkedHashMap<Character, Integer> temp = new LinkedHashMap<>();
				for (Entry<Character, Integer> entry : uniqueChars.entrySet()) {
					if ((entry.getValue() > index)) {
						temp.put(entry.getKey(), entry.getValue());
					}
				}
				temp.put(s.charAt(i), i);
				uniqueChars = temp;
			}
		}
		return maxLength > uniqueChars.size() ? maxLength : uniqueChars.size();
	}

	public static void main(String[] args) {
		System.out.println(new Solution()
				.lengthOfLongestSubstring("qopubjguxhxdipfzwswybgfylqvjzhar"));
	}
}