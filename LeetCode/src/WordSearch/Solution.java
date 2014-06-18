package WordSearch;

public class Solution {

	private int iarray[] = { -1, 0, 1, 0 };
	private int jarray[] = { 0, -1, 0, 1 };

	public boolean exist(char[][] board, String word) {
		if (board == null || board.length < 1 || board[0].length < 1) {
			return false;
		}
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[0].length; ++j) {
				if (board[i][j] != word.charAt(0)) {
					continue;
				}
				if (helper(board, i, j, word, 0,
						new boolean[board.length][board[0].length])) {
					return true;
				}
			}
		}
		return false;
	}

	boolean helper(char[][] board, int x, int y, final String word, int index,
			boolean[][] isUsed) {
		if (index >= word.length()) {
			return true;
		}
		if (!isInRange(x, y, board)) {
			return false;
		}
		if (isUsed[x][y]) {
			return false;
		}
		if (board[x][y] != word.charAt(index)) {
			return false;
		}
		isUsed[x][y] = true;
		for (int i = 0; i < iarray.length; ++i) {
			if (helper(board, x + iarray[i], y + jarray[i], word, index + 1,
					isUsed)) {
				return true;
			}

		}

		return false;
	}

	private boolean isInRange(int x, int y, char[][] board) {
		return x >= 0 && y >= 0 && x < board.length && y < board[0].length;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.exist(
				new char[][] { { 'a', 'b' }, { 'c', 'd' } }, "abcd"));
		// System.out.println(sol.helper("bb"));
	}
}
