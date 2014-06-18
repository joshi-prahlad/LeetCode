/*
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
 */

package RemoveDuplicatesfromSortedListII;

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

public class Solution {

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode newHead = null;
		while (head != null) {
			boolean isD = false;
			while (true) {
				if (head.next != null) {
					if (head.val == head.next.val) {
						isD = true;
						head = head.next;
						continue;
					}
				}
				if (isD) {
					head = head.next;
					isD = false;
					if (head != null) {
						continue;
					}
				}
				break;
			}
			if (head == null) {
				if (prev != null) {
					prev.next = null;
					return newHead;
				} else {
					return null;
				}
			}
			if (prev == null) {
				newHead = head;
			} else {
				prev.next = head;
			}
			prev = head;
			head = head.next;
		}
		return newHead;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.deleteDuplicates(new ListNode(1, new ListNode(2,
				new ListNode(3, new ListNode(3))))).val);
		double x = 1.0;
		System.out.println(x == 1);
	}
}
