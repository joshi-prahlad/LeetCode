/*
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
 */

package PartitionList;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; next = null; } }
 */

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
 * each element of the linked list is either less or bigger than x so given LL
 * is L->G->x->L->G->L now it becomes a problem of in place modifying the list
 * such that all 'L' nodes are together in the begining of the list. Special
 * cases when LL begins with a 'G' or 'x' node and the list consatins only 2 L's
 * (L->L)
 *
 * newHead and newCurNode are pointers to the partitioned list.
 */
public class Solution {
	public ListNode partition(ListNode head, int x) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = null;
		ListNode curNode = head;
		ListNode newCurNode = null, curPrevNode = null;
		while (curNode != null) {
			if (curNode.val < x) {
				if (newHead == null) {
					newHead = newCurNode = curNode;
					// If list beings with 'x' or 'G' node
					if (curPrevNode != null) {
						curPrevNode.next = curNode.next;
						curNode.next = head;
						curNode = curNode.next;
					} else {
						curNode = curNode.next;
						curPrevNode = curNode;
					}
				} else {
					// (L->L)
					if (newCurNode.next == curNode) {
						curPrevNode = curNode;
						newCurNode = curNode;
						curNode = curNode.next;
					} else {
						curPrevNode.next = curNode.next;
						curNode.next = newCurNode.next;
						newCurNode.next = curNode;
						newCurNode = curNode;
						curNode = curPrevNode.next;
					}
				}
			} else {
				curPrevNode = curNode;
				// newCurNode.next = curNode;
				curNode = curNode.next;
				// newCurNode = newCurNode.next;
			}
		}
		if (newHead == null) {
			return head;
		}
		return newHead;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		// ListNode head = new ListNode(1, new ListNode(4, new ListNode(3,
		// new ListNode(2, new ListNode(5, new ListNode(2))))));
		// ListNode head = new ListNode(1, new ListNode(1));
		ListNode head = new ListNode(2, new ListNode(1));
		ListNode newHead = sol.partition(head, 2);
		while (newHead != null) {
			System.out.println(newHead.val);
			newHead = newHead.next;
		}
	}
}