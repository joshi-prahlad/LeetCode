/*
 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */

package GrayCode;

import java.util.ArrayList;
import java.util.Arrays;


public class Solution {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(n==0){
        	result.add(0);
        	return result;
        }
        String [] str = grayCodeRec(n);
        for(int i =0;i<str.length;++i){
        	result.add(strToBinary(str[i]));
        }
        return result;
    }
    
    String [] grayCodeRec(int n){
    	if(n==1){
    		return new String[]{"0","1"};
    	}
    	String [] n_1 = grayCodeRec(n-1);
    	String [] ng = new String[n_1.length*2];
    	for(int i=0;i<n_1.length;++i){
    		ng[i] = "0"+n_1[i];
    		ng[ng.length-i-1] = "1"+n_1[i];
    	}
    	return ng;
    }
    
    int strToBinary(String s){
    	int result =0;
    	for(int i=0;i<s.length();++i){
    		if(s.charAt(i)=='1'){
    		result += 1<<(s.length()-i-1);
    		}
    	}
    	return result;
    }
    public static void main(String args[]){
    	Solution sol = new Solution();
    	System.out.println(Arrays.toString(sol.grayCodeRec(3)));
    	System.out.println(sol.strToBinary("1110"));
    	System.out.println(sol.grayCode(2));
    }
}
