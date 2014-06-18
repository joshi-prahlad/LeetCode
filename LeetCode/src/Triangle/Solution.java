package Triangle;

import java.util.Collections;
import java.util.List;

/*
 * start from the second last row and keep computing possible sum
 * at each element in each row.
 * so a[i,j] = min(a[i,j]+a[i+1,j],a[i,j]+a[i+1,j+1])
 * then return a[0,0]
 */
public class Solution {
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null || triangle.size() == 0) {
			return 0;
		}
		if (triangle.size() == 1) {
			return Collections.min(triangle.get(0));
		}
		for (int i = triangle.size() - 2; i >= 0; --i) {
			List<Integer> row = triangle.get(i);
			List<Integer> nextRow = triangle.get(i + 1);
			for (int j = 0; j < row.size(); ++j) {
				row.set(j,
						Math.min(row.get(j) + nextRow.get(j), row.get(j)
								+ nextRow.get(j + 1)));
			}
		}
		return triangle.get(0).get(0);
	}
}
