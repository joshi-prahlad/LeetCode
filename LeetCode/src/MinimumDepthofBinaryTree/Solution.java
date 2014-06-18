/*
 * Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */

package MinimumDepthofBinaryTree;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	Integer curMin = 1, min = Integer.MAX_VALUE;

	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (isLeaf(root)) {
			if (curMin < min) {
				min = curMin;
			}
		}
		++curMin;
		minDepth(root.left);
		--curMin;
		++curMin;
		minDepth(root.right);
		--curMin;
		return min;
	}

	boolean isLeaf(TreeNode root) {
		return root.left == null && root.right == null;
	}
}
