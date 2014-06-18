/*
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
 */
package LinkedListCycleII;

import java.util.HashSet;
import java.util.Set;

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
	public ListNode detectCycle(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode fast = head, slow = head;
		int n = 1;
		do {
			fast = fast.next;
			if (fast == null) {
				return null;
			}
			fast = fast.next;
			slow = slow.next;
			++n;
		} while (fast != slow && fast != null && slow != null);
		if (fast == null || slow == null) {
			return null;
		}

		/*
		 * Maintain a hasset for seen nodes so far and return the first
		 * duplicate your find.
		 */
		Set<ListNode> seen = new HashSet<ListNode>();
		ListNode node = head;
		while (!seen.contains(node)) {
			seen.add(node);
			node = node.next;
		}
		return node;
		// int cycleLength = 0;
		// ListNode node = slow.next;
		// while (node != slow) {
		// ++cycleLength;
		// node = node.next;
		// }
		// ++cycleLength;
		//
		// int llLength = 2 * n - 1 - cycleLength;
		// ;
		// int beginIndex = llLength - cycleLength;
		// node = head;
		//
		// int temp = cycleLength;
		// while (cycleLength-- > 0) {
		// node = node.next;
		// }
		// if (node == head) {
		// return head;
		// }
		// // System.out.println("begin Index " + beginIndex + " " + cycleLength
		// // + " " + llLength);
		// while (beginIndex > 0) {
		// node = node.next;
		// --beginIndex;
		// }
		// return node;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		// ListNode two = new ListNode(2, new ListNode(0, new ListNode(-4)));
		// two.next.next.next = two;
		// System.out.println(sol.detectCycle(new ListNode(3, two)));
		ListNode one = new ListNode(1, new ListNode(2));
		one.next.next = one;
		System.out.println(sol.detectCycle(one).val);
	}
}
