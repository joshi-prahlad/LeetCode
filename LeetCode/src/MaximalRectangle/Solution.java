/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */
package MaximalRectangle;

class Pair {
	int l;
	int w;
	int area;

	Pair(int l, int w) {
		this.l = l;
		this.w = w;
		this.area = l * w;
	}
}

public class Solution {
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int maxArea = 0;
		Pair[][] count = new Pair[matrix.length][matrix[0].length];
		for (int i = 0; i < count.length; ++i) {
			for (int j = 0; j < count[0].length; ++j) {
				if (i == 0 && j == 0) {
					if (matrix[i][j] == '1') {
						count[i][j] = new Pair(1, 1);
						if (maxArea < count[i][j].area) {
							maxArea = count[i][j].area;
						}
					} else {
						count[i][j] = new Pair(0, 0);
					}
					continue;
				}
				if (i == 0) {
					if (matrix[i][j] == '1') {
						count[i][j] = new Pair(1, count[i][j - 1].w + 1);
						if (maxArea < count[i][j].area) {
							maxArea = count[i][j].area;
						}
					} else {
						count[i][j] = new Pair(0, 0);
					}
					continue;
				}
				if (j == 0) {
					if (matrix[i][j] == '1') {
						count[i][j] = new Pair(count[i - 1][j].l + 1, 1);
						if (maxArea < count[i][j].area) {
							maxArea = count[i][j].area;
						}
					} else {
						count[i][j] = new Pair(0, 0);
					}
					continue;
				}
				if (i != 0 && j != 0) {
					if (matrix[i][j] == '0') {
						count[i][j] = new Pair(0, 0);
						continue;
					}
					int min = Math.min(count[i - 1][j - 1].area, Math.min(
							count[i - 1][j].area, count[i - 1][j - 1].area));
					if (min == count[i - 1][j - 1].area) {
						count[i][j] = new Pair(count[i - 1][j - 1].l + 1,
								count[i - 1][j - 1].w + 1);
						if (maxArea < count[i][j].area) {
							maxArea = count[i][j].area;
						}
						continue;
					}
					if (min == count[i - 1][j].area) {
						count[i][j] = new Pair(count[i - 1][j].l + 1,
								count[i - 1][j].w);
						if (maxArea < count[i][j].area) {
							maxArea = count[i][j].area;
						}
						continue;
					}
					if (min == count[i][j - 1].area) {
						count[i][j] = new Pair(count[i - 1][j].l,
								count[i - 1][j].w + 1);
						if (maxArea < count[i][j].area) {
							maxArea = count[i][j].area;
						}
						continue;
					}
				}
			}
		}
		return maxArea;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.maximalRectangle(new char[][] { { '0', '1' } }));
	}
}
