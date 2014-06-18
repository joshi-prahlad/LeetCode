package RemoveDuplicatesfromSortedList;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	public ListNode deleteDuplicates(ListNode head) {
		ListNode currentNode = head;
		ListNode prevNode = null;
		while (currentNode != null) {
			if (prevNode != null && currentNode.val == prevNode.val) {
				prevNode.next = currentNode.next;
				currentNode.next = null;
				currentNode = prevNode.next;
				continue;
			} else {
				prevNode = currentNode;
				currentNode = currentNode.next;
			}
		}
		return head;
	}
}
