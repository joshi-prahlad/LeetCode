/*
 * Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */
package BestTimetoBuyandSellStock;


public class Solution {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int minArray[] = new int[prices.length];
		int maxArray[] = new int[prices.length];
		minArray[0] = prices[0];
		maxArray[prices.length - 1] = prices[prices.length - 1];
		for (int i = 1, j = prices.length - 2; i < prices.length && j >= 0; ++i, --j) {
			minArray[i] = Math.min(prices[i], minArray[i - 1]);
			maxArray[j] = Math.max(prices[j], maxArray[j + 1]);
		}
		int maxProfit = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; ++i) {
			int profit = maxArray[i] - minArray[i];
			if (profit > maxProfit) {
				maxProfit = profit;
			}
		}
		// System.out.println(Arrays.toString(minArray));
		// System.out.println(Arrays.toString(maxArray));
		return maxProfit;
	}

	public static void main(String args[]) {
		Solution sol = new Solution();
		System.out.println(sol.maxProfit(new int[] { 1, 2 }));
	}
}
