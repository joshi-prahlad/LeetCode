/*
 * Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
package RecoverBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {

	List<TreeNode> swapped = new ArrayList<TreeNode>();
	List<TreeNode> parentList = new ArrayList<TreeNode>();

	public void recoverTree(TreeNode root) {
		inOrder2(root, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
		if (swapped.size() == 2) {
			// if (swapped.get(1) == parentList.get(1).left) {
			// parentList.get(1).left = swapped.get(0);
			// } else {
			// parentList.get(1).right = swapped.get(0);
			// }
			// if (swapped.get(0) == parentList.get(0).left) {
			// parentList.get(0).left = swapped.get(1);
			// } else {
			// parentList.get(0).right = swapped.get(1);
			// }
			int c = swapped.get(0).val;
			swapped.get(0).val = swapped.get(1).val;
			swapped.get(1).val = c;
		} else {
			int c = swapped.get(0).val;
			swapped.get(0).val = parentList.get(0).val;
			parentList.get(0).val = c;
		}
	}

	void inOrder2(TreeNode root, int low, int high, TreeNode parent) {
		if (root == null) {
			return;
		}
		if (root.val > low && root.val < high) {
			inOrder2(root.left, low, root.val, root);
			inOrder2(root.right, root.val, high, root);
		} else {
			swapped.add(root);
			parentList.add(parent);
		}
	}
}
