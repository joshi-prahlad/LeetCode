/*
 * Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
 */
package BinaryTreeMaximumPathSum;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

/*
 * Maintain two sums. One 'maxVal' is the sum of the maximul sum seen so far
 * while return value form maxPathSumHelper is the sume of a path that goes
 * through root node. In the end returns bigger of these two.s
 */
public class Solution {

	int maxVal = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		return Math.max(maxPathSumHelper(root), maxVal);
	}

	public int maxPathSumHelper(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		int left = maxPathSumHelper(root.left);
		int right = maxPathSumHelper(root.right);
		if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE) {

			int treeMax = max(root.val + left, root.val + right, root.val);
			int currentMax = max(treeMax, left, right, root.val + left + right);
			if (currentMax > maxVal) {
				maxVal = currentMax;
			}
			return treeMax;
		}
		if (left == Integer.MIN_VALUE && right == Integer.MIN_VALUE) {
			if (root.val > maxVal) {
				maxVal = root.val;
			}
			return root.val;
		}
		if (left == Integer.MIN_VALUE) {
			int treeMax = max(root.val + right, root.val);
			int currentMax = max(treeMax, right);
			if (currentMax > maxVal) {
				maxVal = currentMax;
			}
			return treeMax;
		}
		int treeMax = max(root.val + left, root.val);
		int currentMax = max(treeMax, left);
		if (currentMax > maxVal) {
			maxVal = currentMax;
		}
		return treeMax;
	}

	int max(int... a) {
		int max = a[0];
		for (int i = 1; i < a.length; ++i) {
			if (a[i] > max) {
				max = a[i];
			}
		}
		return max;
	}
}
