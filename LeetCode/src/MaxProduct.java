public class MaxProduct {

	int compute(int[] arr) {
		int max = Integer.MIN_VALUE;
		int currentPMax = 1, currentNMax = 1;
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] == 0) {
				currentPMax = 1;
				currentNMax = 1;
				if (0 > max) {
					max = 0;
				}
			}
			int newPMax;
			if (arr[i] < 0) {
				newPMax = currentNMax * arr[i];
				currentNMax = currentPMax * arr[i];
			} else {
				currentNMax *= arr[i];
				newPMax = currentPMax * arr[i];
			}

			if (newPMax > 1) {
				currentPMax = newPMax;
			} else {
				currentPMax = 1;
			}
			if (newPMax > max) {
				max = newPMax;
			}
			if (currentNMax > max) {
				max = currentNMax;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		MaxProduct mp = new MaxProduct();
		System.out.println(mp.compute(new int[] { 1, -2, -3, 0, 7, -8, -2 }));
	}
}
