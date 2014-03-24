package addTwoNumbers;

/*You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8*/

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		this.val = x;
		this.next = null;
	}
}

public class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if ((l1 == null) || (l2 == null)) {
			return l1 == null ? l2 : l1;
		}
		boolean isCarry = false;
		ListNode prev = null, result = null;
		while ((l1 != null) && (l2 != null)) {
			ListNode sum = new ListNode(0);
			if (prev != null) {
				prev.next = sum;
			} else {
				result = sum;
			}
			sum.val = l1.val + l2.val + (isCarry ? 1 : 0);
			if (sum.val >= 10) {
				sum.val -= 10;
				isCarry = true;
			} else {
				isCarry = false;
			}
			prev = sum;
			l1 = l1.next;
			l2 = l2.next;
		}
		ListNode rem = l1 == null ? l2 : l1;
		while (rem != null) {
			ListNode sum = new ListNode(rem.val + (isCarry ? 1 : 0));
			if (prev != null) {
				prev.next = sum;
			}
			if (sum.val >= 10) {
				sum.val -= 10;
				isCarry = true;
			} else {
				isCarry = false;
			}
			prev = sum;
			rem = rem.next;
		}
		if (isCarry) {
			ListNode sum = new ListNode(1);
			prev.next = sum;
		}
		return result;
	}

}
