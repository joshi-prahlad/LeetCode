package Util;

public class LCS {

	public int length(String a, String b) {
		int[][] mat = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i < mat.length; ++i) {
			for (int j = 0; j < mat[0].length; ++j) {
				if (i == 0 || j == 0) {
					mat[i][j] = 0;
				} else {
					if (a.charAt(i - 1) == b.charAt(j - 1)) {
						mat[i][j] = mat[i - 1][j - 1] + 1;
					} else {
						mat[i][j] = mat[i][j - 1] > mat[i - 1][j] ? mat[i][j - 1]
								: mat[i - 1][j];
					}
				}
			}
		}
		return mat[mat.length - 1][mat[0].length - 1];
	}

	public static void main(String args[]) {
		LCS lcs = new LCS();
		System.out.println(lcs.length("aadbbbaccc", "dbbca"));
	}
}
