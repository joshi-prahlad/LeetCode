package Pow_x_n;

/*
 * Implement pow(x, n).
 */
public class Solution {
	public double pow(double x, int n) {
		if (x == 0) {
			return x;
		}
		if (n == 0) {
			return 1;
		}
		if (x == 1) {
			return 1;
		}
		if (x == -1) {
			return (n % 2 == 0 ? 1 : -1);
		}
		if (n < 0) {
			return 1 / pow(x, -n);
		}
		if (n % 2 == 0) {
			double result = pow(x, n / 2);
			return result * result;
		} else {
			return x * pow(x, n - 1);
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.pow(1, Integer.MIN_VALUE));

	}
}
