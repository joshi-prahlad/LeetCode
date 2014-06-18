/*
 * Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
 */

package PopulatingNextRightPointersII;

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}

	public TreeLinkNode(int x, TreeLinkNode left, TreeLinkNode right) {
		this.val = x;
		this.left = left;
		this.right = right;
	}
}

public class Solution {
	public void connect(TreeLinkNode root) {
		inOrder(root, null);
	}

	void inOrder(TreeLinkNode node, TreeLinkNode parent) {
		if (node == null) {
			return;
		}
		if (parent == null) {
			node.next = null;
		}
		inOrder(node.left, node);
		if (parent != null) {
			if (parent.left == node) {
				node.next = parent.right != null ? parent.right : parent.left;
			} else {
				if (parent.next != null) {
					node.next = parent.next.left != null ? parent.next.left
							: parent.next.right;
				} else {
					node.next = null;
				}
			}
		}
		inOrder(node.right, node);
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		sol.connect(new TreeLinkNode(1, new TreeLinkNode(2), null));
		sol.connect(new TreeLinkNode(1, null, new TreeLinkNode(2)));
	}
}
