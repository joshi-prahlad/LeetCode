/*
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 */
package PermutationSequence;

/*
 * Have an array of size n with all numbers in increasing order.
 *Each number comes in first place (n-1)! times.
 *So to find out which number comes in the first index in kth
 *permutations take ceiling of k/(n-1)!. Lets' call it rank.
 *Now remove this element from the array.
 *The updated k for this new array would be
 * k = k - (rank)*(n-1)!
 * now call it recursively.
 */
public class Solution {
	int fact[];

	public String getPermutation(int n, int k) {
		fact = new int[n];
		init(n);
		int[] arr = new int[n];
		for (int i = 1; i <= n; ++i) {
			arr[i - 1] = i;
		}
		return getPermutationHelper(arr, k);
	}

	public String getPermutationHelper(int arr[], int k) {
		if (k == 0 || arr == null || arr.length == 0) {
			return "";
		}
		if (arr.length == 1) {
			return arr[0] + "";
		}
		int n = arr.length;
		// System.out.println("fact " + Arrays.toString(fact) + " " + n);
		int rank = (int) Math.ceil(k * 1.0 / fact[n - 1 - 1]);
		// System.out.println("rank " + rank + " k " + k + " n " + n);
		if (rank == 0) {
			rank = 1;
		}
		int rest = k - (rank - 1) * fact[n - 1 - 1];
		return arr[rank - 1] + ""
		+ getPermutationHelper(getRest(arr, rank - 1), rest);
	}

	int[] getRest(int[] arr, int r) {
		// System.out.println("r " + r + " " + arr[r]);
		int newAr[] = new int[arr.length - 1];
		for (int i = 0; i < arr.length; ++i) {
			if (i < r) {
				newAr[i] = arr[i];
			} else {
				if (i > r) {
					newAr[i - 1] = arr[i];
				}
			}
		}
		// System.out.println(Arrays.toString(newAr));
		return newAr;
	}

	void init(int n) {

		fact[0] = 1;
		for (int i = 1; i < n - 1; ++i) {
			fact[i] = (i + 1) * fact[i - 1];
		}
	}

	public static void main(String args[]) {
		// System.out.println(Math.ceil(3 / 2));
		Solution sol = new Solution();
		System.out.println(sol.getPermutation(4, 13));
	}
}
