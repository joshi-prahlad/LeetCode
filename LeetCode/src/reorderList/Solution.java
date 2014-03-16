/*	Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.*/

/*
 * The basic idea is to avoid traversing the list again and again
 * to find the last and second last node.
 * We traverse the list once and create  a map from index to node
 * and use it to find the last and second last node in each iteration.
 * 
 * Time complexity O(n)
 * Space complexity O(n)
 */
package reorderList;

import java.util.HashMap;
import java.util.Map;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		this.val = x;
		this.next = null;
	}

	ListNode(int x, ListNode next) {
		this.val = x;
		this.next = next;
	}
}

public class Solution {

	void reorderList(ListNode node) {
		if ((node == null) || (node.next == null) || (node.next.next == null)) {
			return;
		}
		Map<Integer, ListNode> indexToNode = new HashMap<>();
		int len = this.populateMap(indexToNode, node);
		this.reorderListHelper(node, indexToNode, len, 0);

	}

	private void reorderListHelper(ListNode node,
			Map<Integer, ListNode> indexToNode, int len, int iteration) {
		ListNode lastNode = indexToNode.get(len - 1 - iteration), secondLastNode = indexToNode
				.get(len - 2 - iteration);
		if (((len - (iteration * 2)) <= 2)) {
			return;
		}
		secondLastNode.next = null;
		ListNode newNode = node.next;
		node.next = lastNode;
		lastNode.next = newNode;
		this.reorderListHelper(newNode, indexToNode, len, iteration + 1);
	}

	private int populateMap(Map<Integer, ListNode> indexToNode, ListNode node) {
		int i = 0;
		for (; node != null; node = node.next, ++i) {
			indexToNode.put(i, node);
		}
		return i;
	}

	public static void main(String args[]) {
		new Solution().reorderList(new ListNode(1, new ListNode(2,
				new ListNode(3))));
	}
}
