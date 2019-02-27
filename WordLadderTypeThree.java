import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderTypeThree {
	// find one of all the shortest paths
	public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
		if(beginWord == null || endWord == null || wordList == null) return new ArrayList<>();
		Set<String> dict = new HashSet<>();
		for(String word: wordList) {
			dict.add(word);
		}
		List<String> res = new LinkedList<>();
		if(!dict.contains(endWord)) return res;
		Map<String,String> graph = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size -- > 0) {
				String curr = queue.poll();
				char[] charsCurr = curr.toCharArray();
				for(int i = 0; i < charsCurr.length; i++) {
					char tmp = charsCurr[i];
					for(char ch = 'a'; ch<= 'z'; ch++) {
						charsCurr[i]= ch;
						String transWord = String.valueOf(charsCurr);
						if(ch != tmp && dict.contains(transWord)) {
							graph.put(transWord, curr);
							if(transWord.equals(endWord)) {
								String word = endWord;
								while(word != null) {
									res.add(0,word);
									word = graph.get(word);
								}
								return res;
							}else {
								dict.remove(transWord);
								queue.offer(transWord);
							}
						}
					}
					charsCurr[i] = tmp;
				}

			}
		}
		return res;
	}
	// two directions 
	public List<String> findLaddersTwo(String beginWord, String endWord, List<String> wordList){
		if(beginWord == null || endWord == null || wordList == null) return new ArrayList<>();
		Set<String> dict = new HashSet<>();
		for(String word: wordList) {
			dict.add(word);
		}
		List<String> res = new LinkedList<>();
		if(!dict.contains(endWord)) return res;
		dict.remove(endWord);
		Map<String,String> bGraph = new HashMap<>();
		Map<String,String> eGraph = new HashMap<>();
		Set<String> bSet = new HashSet<>();
		Set<String> eSet = new HashSet<>();
		bSet.add(beginWord);
		eSet.add(endWord);
		boolean beginToEnd = true;
		while(!bSet.isEmpty() && !eSet.isEmpty()) {
			if(bSet.size() > eSet.size()) {
				Set<String> tmp = bSet;
				bSet = eSet;
				eSet = tmp;
				beginToEnd = false;
			}
			for(String curr: bSet) {
				Set<String> nextLevel = new HashSet<>();
				char[] charsCurr = curr.toCharArray();
				for(int i = 0; i < charsCurr.length; i++) {
					char tmp = charsCurr[i];
					for(char ch = 'a'; ch <= 'z'; ch++) {
						charsCurr[i] = ch;
						String transWord = String.valueOf(charsCurr);
						if(ch != tmp && ( dict.contains(transWord) || eSet.contains(transWord))) {
							if(eSet.contains(transWord)) {
								if(beginToEnd) {
									String word = curr;
									while(word != null) {
										res.add(0,word);
										word = bGraph.get(word);
									}
									word = transWord;
									while(word != null) {
										word = eGraph.get(word);
										res.add(word);
									}	
								}else {
									String word = transWord;
									while(word != null) {
										res.add(0,word);
										word = bGraph.get(word);
									}
									word = curr;
									while(word != null) {
										res.add(word);
										word = eGraph.get(word);
									}
								}
								return res;
							}
							if(!nextLevel.contains(transWord)) {
								nextLevel.add(transWord);
							}
							if(beginToEnd) {
								bGraph.put(transWord, curr);
							}else {
								eGraph.put(transWord, curr);
							}
							
						}
						
					}
					charsCurr[i] = tmp;
				}
				bSet = nextLevel;
				dict.removeAll(nextLevel);
			}
			
		}
		return res;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordLadderTypeThree test = new WordLadderTypeThree();
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		List<String> res =test.findLadders(beginWord, endWord, wordList);
		List<String> resTwo =test.findLaddersTwo(beginWord, endWord, wordList);
		for(String str: res) {
			System.out.print(str + " ");
		}
		System.out.println();
		for(String str: resTwo) {
			System.out.print(str + " ");
		}
	}

}
