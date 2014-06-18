import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

public class Test {

	static int singleNumber(int A[], int n) {
		int count[] = new int[32];
		int result = 0;
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < n; j++) {
				if (((A[j] >> i) & 1) == 1) {
					count[i]++;
				}
			}
			result |= ((count[i] % 3) << i);
		}
		return result;
	}

	static public int singleNumber(int[] A) {
		if (A == null)
			return 0;
		int x0 = ~0, x1 = 0, x2 = 0, t;
		for (int i = 0; i < A.length; i++) {
			t = x2;
			x2 = (x1 & A[i]) | (x2 & ~A[i]);
			x1 = (x0 & A[i]) | (x1 & ~A[i]);
			x0 = (t & A[i]) | (x0 & ~A[i]);
		}
		return x1;
	}

	public static void main(String[] args) {
		Integer a = new Integer(3);
		Integer b = new Integer(3);
		Object o1 = a;
		Object o2 = b;

		System.out.println(o1 == o2);

	}

	public static void check_anagrams(String[] firstWords, String[] secondWords) {
		// Write your code here
		// To print results to the standard output you can use
		// System.out.println()
		// Example: System.out.println("Hello world!");
		for (int i = 0; i < firstWords.length; ++i) {
			System.out
			.println(isAnagram(firstWords[i], secondWords[i]) ? 1 : 0);
		}

	}

	static boolean isAnagram(String one, String two) {
		if (one.length() != two.length()) {
			return false;
		}
		HashMap<Character, Integer> chars1 = new HashMap<Character, Integer>();
		HashMap<Character, Integer> chars2 = new HashMap<Character, Integer>();
		for (int i = 0; i < one.length(); ++i) {
			Integer count = chars1.get(one.charAt(i));
			if (count == null) {
				count = new Integer(1);
			} else {
				++count;
			}
			chars1.put(one.charAt(i), count);
			count = chars2.get(two.charAt(i));
			if (count == null) {
				count = new Integer(1);
			} else {
				++count;
			}
			chars2.put(two.charAt(i), count);
		}
		System.out.println(chars1 + " " + chars2);
		for (Entry<Character, Integer> e : chars1.entrySet()) {
			Integer temp = chars2.get(e.getKey());
			if (temp == null || !temp.equals(e.getValue())) {
				// System.out.println(temp + " " + e.getValue() + " " +
				// e.getKey()
				// + " " + (temp.equals(e.getValue())));
				return false;
			}
		}
		return true;

	}

	public static void check_braces(String[] expressions) {
		// Write your code here
		// To print results to the standard output you can use
		// System.out.println()
		// Example: System.out.println("Hello world!");
		Stack<Character> stack = new Stack<Character>();

		for (String expr : expressions) {
			if (expr.length() % 2 != 0 || expr == null || expr.isEmpty()) {
				System.out.println(0);
				continue;
			}
			boolean result = true;
			for (Character c : expr.toCharArray()) {
				if (c.equals('(') || c.equals('[') || c.equals('{')) {
					stack.push(c);
				} else {
					// System.out.println(" " + c);
					// System.out.println(stack);
					if (stack.isEmpty()) {
						result = false;
						break;
					} else {
						Character el = stack.pop();
						if (!((el.equals('(') && c.equals(')'))
								|| (el.equals('[') && c.equals(']')) || (el
										.equals('{') && c.equals('}')))) {
							result = false;
							break;
						}
					}
				}
			}
			if (result) {
				if (stack.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			} else {
				System.out.println(0);
			}
		}
	}

	public static void find_deviation(Integer[] v, Integer d) {
		// Write your code here
		// To print results to the standard output you can use
		// System.out.println()
		// Example: System.out.println("Hello world!");

		int[] min = new int[v.length];
		min[0] = v[0];
		int[] max = new int[v.length];
		max[0] = v[0];
		for (int i = 1; i < v.length; ++i) {
			if (i % d == 0) {
				min[i] = v[i];
			} else {
				min[i] = Math.min(v[i], min[i - 1]);
			}
		}

	}
}
