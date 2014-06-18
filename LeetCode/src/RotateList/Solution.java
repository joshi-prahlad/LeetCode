/*
 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */
package RotateList;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	ListNode(int x, ListNode next) {
		val = x;
		this.next = next;
	}
}

/*
 * Find the k+1thNode from end of the list and update the pointers to make the
 * kth node from last as the new head.
 */
public class Solution {
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || k == 0) {
			return head;
		}
		int listLength = 0;
		ListNode current = head;
		while (current != null) {
			++listLength;
			current = current.next;
		}
		k = k % listLength;
		if (k == 0) {
			return head;
		}
		current = head;
		ListNode kPlus1thNodeFromLast = head, lastNode = null;

		int currentIndex = 1;
		while (current != null) {
			if (currentIndex > k + 1) {
				if (kPlus1thNodeFromLast == null) {
					return head;
				}
				kPlus1thNodeFromLast = kPlus1thNodeFromLast.next;
			}
			if (current.next == null) {
				lastNode = current;
			}
			current = current.next;
			++currentIndex;
		}
		// System.out.println(" " + kPlus1thNodeFromLast.val);
		ListNode newHead = kPlus1thNodeFromLast.next;
		kPlus1thNodeFromLast.next = null;
		lastNode.next = head;
		return newHead;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.rotateRight(new ListNode(1, new ListNode(2,
				new ListNode(3, new ListNode(4, new ListNode(5))))), 6).val);
		System.out
		.println(sol.rotateRight(new ListNode(1, new ListNode(2)), 3).val);
	}

}
