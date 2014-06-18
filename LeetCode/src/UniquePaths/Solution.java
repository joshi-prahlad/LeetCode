package UniquePaths;

/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?

 Note: m and n will be at most 100.
 */

/*
 * Maintain another count matrix such that
 * count[i,j] is number of ways of reaching destination from row i and col j.
 * initialize last row and last column of count matrix to 1.
 * after that starting from the second last column and second last row
 * of count do this
 *  count[i][j]=count[i+1][j] + count[i][j+1]
 */
public class Solution {
	public int uniquePaths(int m, int n) {
		// return helper(m, n, 1, 1);
		if (m == 0 || n == 0) {
			return 0;
		}
		if (m < 2 || n < 2) {
			return 1;
		}
		int[][] count = new int[m][n];
		for (int i = 0; i < m; ++i) {
			count[i][n - 1] = 1;
		}
		for (int i = 0; i < n; ++i) {
			count[m - 1][i] = 1;
		}
		for (int col = n - 2; col >= 0; --col) {
			for (int row = m - 2; row >= 0; --row) {
				count[row][col] = count[row + 1][col] + count[row][col + 1];
			}
		}
		return count[0][0];
	}

	// recursive solution
	int helper(int m, int n, int row, int col) {
		if (row > m || col > n) {
			return 0;
		}
		if (row == m || col == n) {
			return 1;
		}
		return helper(m, n, row + 1, col) + helper(m, n, row, col + 1);
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.uniquePaths(6, 4));
	}
}
