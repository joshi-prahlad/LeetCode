package RotateImage;

/*
 * You are given an n x n 2D matrix representing an image.

 Rotate the image by 90 degrees (clockwise).

 Follow up:
 Could you do this in-place?


 */
public class Solution {
	public void rotate(int[][] matrix) {
		int[][] rot = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				rot[j][matrix.length - i - 1] = matrix[i][j];
			}
		}
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				matrix[i][j] = rot[i][j];
			}
		}
	}
}
