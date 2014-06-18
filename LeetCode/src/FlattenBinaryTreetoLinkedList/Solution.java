/*
 * Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
 */
package FlattenBinaryTreetoLinkedList;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		helper(root);
	}

	/*
	 * Helper returns the rightmost node in the flattened tree and flattens the
	 * tree.
	 */
	TreeNode helper(TreeNode node) {
		if (node == null) {
			return null;
		}
		TreeNode left = helper(node.left);
		TreeNode right = helper(node.right);
		if (left != null) {
			left.right = node.right;
		}
		if (node.left != null) {
			node.right = node.left;
			node.left = null;
		}
		if (right == null && left == null) {
			return node;
		}
		if (right != null) {
			return right;
		}
		return left;
	}
}
