/*
 * Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
 */
package UniquePathsII;

public class Solution {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		if (m == 0 || n == 0) {
			return 0;
		}
		// if (m == 1 && n == 1) {
		// return 0;
		// }
		// if (m < 2 || n < 2) {
		// return 1;
		// }
		int[][] count = new int[m][n];
		for (int i = m - 1; i >= 0; --i) {
			if (obstacleGrid[i][n - 1] == 1) {
				count[i][n - 1] = 0;
			} else {
				if (i == m - 1) {
					count[i][n - 1] = 1;
				} else {
					count[i][n - 1] = count[i + 1][n - 1];
				}
			}

		}
		for (int i = n - 1; i >= 0; --i) {
			if (obstacleGrid[m - 1][i] == 1) {
				count[m - 1][i] = 0;
			} else {
				if (i == n - 1) {
					count[m - 1][n - 1] = 1;
				} else {
					count[m - 1][i] = count[m - 1][i + 1];
				}
			}
		}
		for (int col = n - 2; col >= 0; --col) {
			for (int row = m - 2; row >= 0; --row) {
				if (obstacleGrid[row][col] == 1) {
					count[row][col] = 0;
				} else {
					count[row][col] = count[row + 1][col] + count[row][col + 1];
				}
			}
		}
		return count[0][0];
	}

	public static void main(String args[]) {
		System.out.println(new Solution().uniquePathsWithObstacles(new int[][] {
				{ 0 }, { 1 } }));
	}
}
