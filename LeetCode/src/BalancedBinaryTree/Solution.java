/*
 * Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.


 */

package BalancedBinaryTree;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

class LinkedListNode {
	int val;
	LinkedListNode next;
}

public class Solution {

	public boolean isBalanced(TreeNode root) {
		return helper(root, new Wrap(0));
	}

	boolean helper(TreeNode root, Wrap height) {
		if (root == null) {
			height.i = 0;
			return true;
		}
		Wrap leftHeight = new Wrap(0), rightHeight = new Wrap(0);
		boolean isLeft = helper(root.left, leftHeight);
		boolean isRight = helper(root.right, rightHeight);
		height.i = Math.max(leftHeight.i, rightHeight.i) + 1;
		if (!isLeft || !isRight) {
			return false;
		}
		if (Math.abs(leftHeight.i - rightHeight.i) > 1) {
			return false;
		}
		return true;
	}

	class Wrap {
		int i;

		Wrap(int i) {
			this.i = i;
		}
	}
}
