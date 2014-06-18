import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Write a function that takes two parameters:
 (1) a String representing the contents of a text document
 (2) an integer providing the number of items to return

 - Implement the function such that it returns a list of Strings ordered by word frequency, the most frequently occurring word first.
 - Use your best judgement to decide how words are separated.
 - Implement this function as you would for a production / commercial system
 - You may use any standard data structures.
 - Please use Java for the solution.

 Performance Constraints:
 - Your solution should run in O(n) time where n is the number of characters in the document.
 - Please provide reasoning on how the solution obeys the O(n) constraint.

 */

public class TopItemCounter {

	private static final class Term {
		String lexem;
		public int freq;

		Term(final String lexem, final int freq) {
			this.lexem = lexem;
			this.freq = freq;
		}
	}

	private static final class TermComparator implements Comparator<Term> {

		public int compare(Term a, Term b) {
			return a.freq - b.freq;
		}
	}

	public List<String> getTopItems(final String text, final int itemCount) {
		// A contiguous sequence of alphanumeric characters is treated as a
		// single word.
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(text);
		Map<String, Integer> wordToFreq = new HashMap<String, Integer>();
		while (matcher.find()) {
			final String currentWord = matcher.group();
			Integer count = wordToFreq.get(currentWord);
			if (count == null) {
				count = new Integer(1);
			} else {
				++count;
			}
			wordToFreq.put(currentWord, count);
		}
		// Build a min heap of size 'itemCount'
		PriorityQueue<Term> minHeap = new PriorityQueue<Term>(itemCount,
				new TermComparator());
		int currentItemIndex = 0;
		for (Entry<String, Integer> e : wordToFreq.entrySet()) {
			if (currentItemIndex < itemCount) {
				minHeap.add(new Term(e.getKey(), e.getValue()));
				++currentItemIndex;
			} else {
				Term minFreqTerm = minHeap.peek();
				if (minFreqTerm.freq < e.getValue()) {
					minHeap.remove();
					minHeap.add(new Term(e.getKey(), e.getValue()));
				}
			}
		}
		// As min heap would return the values in increasing order
		// reverse the order before returning.
		String arr[] = new String[itemCount];
		currentItemIndex = itemCount - 1;
		while (!minHeap.isEmpty()) {
			arr[currentItemIndex--] = minHeap.poll().lexem;
		}
		return Arrays.asList(arr);
	}

	// Test
	public static void main(String args[]) {
		TopItemCounter aTopItemCounter = new TopItemCounter();
		System.out.println(aTopItemCounter.getTopItems(
				"abc def ghi a abc b b.ghi:ghi, ghi ", 2));
	}
}