/*
 * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 */
package ValidateBinarySearchTree;

/**
 * Definition for binary tree public class TreeNode { int val; TreeNode left;
 * TreeNode right; TreeNode(int x) { val = x; } }
 */

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	public boolean isValidBST(TreeNode root) {
		return inOrder2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean inOrder(TreeNode root, Integer prevVal) {
		if (root == null) {
			return true;
		}
		boolean isLeft = inOrder(root.left, prevVal);
		if (isLeft == false) {
			return false;
		}
		if (root.val < prevVal) {
			return false;
		}
		prevVal = root.val;
		return inOrder(root.right, root.val);
	}

	boolean inOrder2(TreeNode root, int low, int high) {
		if (root == null) {
			return true;
		}
		if (root.val > low && root.val < high) {
			return inOrder2(root.left, low, root.val)
					&& inOrder2(root.right, root.val, high);
		} else {
			return false;
		}
	}
}
