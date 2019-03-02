package LeetCodeBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode17LetterCombinationsOfaPhoneNumber {

	private static final String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
    	List<String> res = new LinkedList<>();
    	if(digits == null || digits.length() == 0) return res;
    	res.add("");
    	for(int i = 0; i< digits.length(); i++) {
    		int digit = Character.getNumericValue(digits.charAt(i));
    		String letters = map[digit];
    		int size = res.size();
    		while(size -- > 0) {
    			String curr = res.remove(0);
    			for(int j = 0; j<letters.length(); j++) {
    				res.add(curr + letters.charAt(j));
    			}
    		}
    	}
      
    	return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeetCode17LetterCombinationsOfaPhoneNumber test = new LeetCode17LetterCombinationsOfaPhoneNumber();
		String digits = "23";
		List<String> res= test.letterCombinations(digits);
		for(String str: res) {
			System.out.println(str);
		}
	}

}
