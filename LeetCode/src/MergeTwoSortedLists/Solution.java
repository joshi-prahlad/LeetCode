/*
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */

package MergeTwoSortedLists;

/* Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode mergeHead = null, currentHead = null;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				if (currentHead == null) {
					mergeHead = currentHead = l1;
				} else {
					currentHead.next = l1;
					currentHead = l1;
				}
				l1 = l1.next;
				currentHead.next = null;
			} else {
				if (currentHead == null) {
					mergeHead = currentHead = l2;
				} else {
					currentHead.next = l2;
					currentHead = l2;
				}
				l2 = l2.next;
				currentHead.next = null;
			}
		}
		if (l1 != null) {
			currentHead.next = l1;
		}
		if (l2 != null) {
			currentHead.next = l2;
		}
		return mergeHead;
	}
}