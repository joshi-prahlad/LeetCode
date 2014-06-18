//package Q3;

import java.util.Scanner;

/*
 * Given a list of integers, your task is to write a program to output an integer-valued list of equal length such that the output element at index 'i' is the product of all input elements except for the input element at 'i'.

 In other words, let inputArray by an integer array of length 'n'. The solution,computed into outputArray, would be:

 for each j from 1 to n-2:

 outputArr[ j ] = inputArray[0] * inputArray[1] * inputArray[2] * ... * inputArray[j-1] * inputArray[j+1] * inputArray[j+2] *...* inputArray[n-1]

 for j = 0

 outputArray[0] = inputArray[1] * outputArray[2] * ... * outputArray[n-1]

 for j = n-1

 outputArray[n-1] = outputArray[0] * outputArray[1] * outputArray[2] * ... * outputArray[n-2]

 As an example, if inputArray = { 1, 2, 3, 4 }, then

 outputArray = { 2*3*4, 1*3*4, 1*2*4, 1*2*3 }.

 Your program should run in O(n) time and should be space efficient.

 Input format

 First line of input contains N , number of elements in list.

 Next N lines will each contain an element (a signed integer)

 Output format

 Print the output list of numbers.
 */
public class Solution {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = scan.nextInt();
		}
		scan.close();
		int lProd[] = new int[n];
		int rProd[] = new int[n];
		lProd[0] = 1;
		rProd[n - 1] = 1;
		for (int i = 1; i < n; ++i) {
			lProd[i] = lProd[i - 1] * arr[i - 1];
			rProd[n - i - 1] = rProd[n - i] * arr[n - i];
		}
		arr[0] = rProd[0];
		arr[n - 1] = lProd[n - 1];
		for (int i = 1; i < n - 1; ++i) {
			arr[i] = lProd[i] * rProd[i];
		}
		for (int el : arr) {
			System.out.println(el);
		}
	}
}
