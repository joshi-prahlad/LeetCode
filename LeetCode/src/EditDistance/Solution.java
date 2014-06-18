package EditDistance;

/*
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:

 a) Insert a character
 b) Delete a character
 c) Replace a character
 */
public class Solution {
	public int minDistance(String word1, String word2) {
		return dist(word1, word2);
	}

	public int dist(String word1, String word2) {
		int[][] matrix = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[0].length; ++j) {
				if (i == 0) {
					matrix[0][j] = j;
					continue;
				}
				if (j == 0) {
					matrix[i][0] = i;
					continue;
				}
				matrix[i][j] = Math.min(matrix[i - 1][j] + 1,
						Math.min(
								matrix[i][j - 1] + 1,
								matrix[i - 1][j - 1]
										+ (word1.charAt(i - 1) == word2
												.charAt(j - 1) ? 0 : 1)));
			}
		}
		return matrix[matrix.length - 1][matrix[0].length - 1];
	}
}