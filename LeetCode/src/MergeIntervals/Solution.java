/*
 * Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
 */

package MergeIntervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

class ICompare implements Comparator<Interval> {

	public int compare(Interval o1, Interval o2) {
		return o1.start - o2.start;
	}
}

public class Solution {
	public List<Interval> merge(List<Interval> intervals) {
		// Sort the intervals by start time.
		Collections.sort(intervals, new ICompare());
		List<Interval> result = new ArrayList<Interval>();
		if (intervals == null || intervals.size() == 0 || intervals.size() == 1) {
			return intervals;
		}

		Interval prev = intervals.get(0);
		boolean lastMerged = false;
		Interval prevMerged = null;
		for (int i = 0; i < intervals.size() - 1;) {
			// Keep them merging as long as they overlap
			// and dont add anything to the result list yet.
			if (intervals.get(i + 1).start <= prev.end) {
				lastMerged = true;
				Interval merged = new Interval(prev.start, Math.max(
						intervals.get(i + 1).end, prev.end));
				// result.add(merged);
				prev = merged;
				prevMerged = merged;
				++i;
			} else {
				if (prevMerged != null) {
					// add the merged interval to the result
					result.add(prevMerged);
					prevMerged = null;
				} else {
					result.add(prev);
				}
				lastMerged = false;
				++i;
				prev = intervals.get(i);
			}
		}
		// if there are only two intervals in input and they overlap
		if (prevMerged != null) {
			result.add(prevMerged);
		}
		// if the last interval doesn't overlap
		if (!lastMerged) {
			result.add(intervals.get(intervals.size() - 1));
		}
		return result;
	}
}
