/*
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
 */
package LinkedListCycle;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	ListNode(int x, ListNode node) {
		val = x;
		next = node;
	}
}

public class Solution {
	public boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode fast = head, slow = head;

		do {
			fast = fast.next;
			if (fast == null) {
				return false;
			}
			fast = fast.next;
			slow = slow.next;

		} while (fast != slow && fast != null && slow != null);
		if (fast == null || slow == null) {
			return false;
		}

		return true;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		// ListNode two = new ListNode(2, new ListNode(0, new ListNode(-4)));
		// two.next.next.next = two;
		// System.out.println(sol.detectCycle(new ListNode(3, two)));
		ListNode one = new ListNode(1, new ListNode(2));
		one.next.next = one;
		// System.out.println(sol.detectCycle(one).val);
	}
}
