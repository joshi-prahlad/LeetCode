/*
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */

/*
 * count[i][j]= min(count[i-1][j]+grid[i][j], count[i][j-1]+grid[i][j]
 */
package MinimumPathSum;

public class Solution {
	public int minPathSum(int[][] grid) {
		int[][] count = new int[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				if (i == 0 && j == 0) {
					count[0][0] = grid[0][0];
					continue;
				}
				if (i == 0) {
					count[0][j] = count[0][j - 1] + grid[0][j];
					continue;
				}
				if (j == 0) {
					count[i][0] = count[i - 1][0] + grid[i][0];
					continue;
				}
				count[i][j] = Math.min(count[i - 1][j] + grid[i][j],
						count[i][j - 1] + grid[i][j]);
			}
		}
		return count[count.length - 1][count[0].length - 1];
	}
}
