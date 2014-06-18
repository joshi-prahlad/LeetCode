/*
 * Merge Sorted Array Total Accepted: 13442 Total Submissions: 42509 My Submissions
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
 */
package MergeSortedArrays;

public class Solution {
	 public void merge(int A[], int m, int B[], int n) {
	        int aIndex =m-1,bIndex =n-1;
	        int mergeIndex = m+n-1;
	        while(aIndex>=0&&bIndex>=0){
	        	if(A[aIndex]>=B[bIndex]){
	        		A[mergeIndex--] =A[aIndex--];
	        	}else{
	        		A[mergeIndex--] = B[bIndex--];
	        	}
	        }
	        while(bIndex>=0){
	        	A[mergeIndex--] = B[bIndex--];
	        }
	    }
}
