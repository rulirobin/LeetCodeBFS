import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LeetCode127WordLadder {
	// one direction 
	// assume the input dict could be edited
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord == null || endWord == null || wordList == null) return 0;
        if(beginWord == endWord) return 1;
        Set<String> dict = new HashSet<>();
        for(String str : wordList) {
        	dict.add(str);
        }
        int minLen = 1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	while(size -- > 0) {
        		String curr = queue.poll();
        		char[] charsCurr = curr.toCharArray();
        		for(int i = 0; i<charsCurr.length; i++) {
        			char tmp = charsCurr[i];
         			for(char ch = 'a'; ch <='z'; ch++) {
        				charsCurr[i] = ch;
        				String nei = String.valueOf(charsCurr);
        				if(dict.contains(nei)) {	
        					if(nei.equals(endWord)) {
        						return minLen + 1;
        					}
        					queue.offer(nei);
        					dict.remove(nei);
        				}
        			}
         			charsCurr[i] = tmp;
        		}
        	}
        	minLen++;	
        }
        return 0;
    }
    // two direction
    // use hashset to store the string because of 0(1) search 
    // corner case: endword is not in the dictionary. 
    public int ladderLengthTwoDirection(String beginWord, String endWord, List<String> wordList) {
        if(beginWord == null || endWord == null || wordList == null) return 0;
        if(beginWord == endWord) return 1;
        Set<String> dict = new HashSet<>();
        for(String str : wordList) {
        	dict.add(str);
        }
        // corner case
        if(!dict.contains(endWord)) return 0;
        int minLen = 1;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        while(!beginSet.isEmpty() && !endSet.isEmpty() ) {      	
        	if(beginSet.size() > endSet.size()) {
        		Set<String> tmp = beginSet;
        		beginSet = endSet;
        		endSet = tmp;
        	}
        	Set<String> nextLevel = new HashSet<>();
        	for(String curr: beginSet) {
        		char[] charsCurr = curr.toCharArray();
        		for(int i = 0; i<charsCurr.length; i++) {
        			char tmp = charsCurr[i];
         			for(char ch = 'a'; ch <='z'; ch++) {
        				charsCurr[i] = ch;
        				String nei = String.valueOf(charsCurr);	
        					if(endSet.contains(nei)) {
        						System.out.println(nei);
        						return minLen + 1;
        					}
        					if(dict.contains(nei)) {
        						nextLevel.add(nei);
        						dict.remove(nei);
        					}

        				}
         			charsCurr[i] = tmp;
        		}
        	}
        	beginSet = nextLevel;
        	minLen++;	
        }
        return 0;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeetCode127WordLadder test = new LeetCode127WordLadder();
		String beginWord = "hot";
		String endWord = "hot";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		System.out.println(test.ladderLength(beginWord, endWord, wordList));
		System.out.println(test.ladderLengthTwoDirection(beginWord, endWord, wordList));
	}

}
