import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LeetCode126WordLadderTypeTwo {
	//one direction
	//BFS to find the shortest length and configure the graph, DFS to find all the path
	// use HashMap to represent graph. each string is the key, the value is the list of its transformed strings. 
	// 
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(beginWord == null || endWord == null || wordList == null) return new ArrayList<>();
        Set<String> dict = new HashSet<>();
        for(String word: wordList) {
        	dict.add(word);
        }
        Map<String,List<String>> graph = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        boolean flag = false;
        List<List<String>> res = new ArrayList<>();
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	Set<String> visitedLevel = new HashSet<>();
        	while(size -- > 0) {
        		String curr = queue.poll();
        		char[] charsCurr = curr.toCharArray();
        		for(int i = 0; i < charsCurr.length; i++) {
        			char tmp = charsCurr[i];
        			for(char ch = 'a'; ch <= 'z'; ch++) {
        				charsCurr[i] = ch;
        				String transWord = String.valueOf(charsCurr);
        			
        				if(ch != tmp && dict.contains(transWord)) {
        					if(transWord.equals(endWord)) flag = true;
        					if(!visitedLevel.contains(transWord)) {
        						List<String> list = new ArrayList<>();
        						list.add(curr);
        						graph.put(transWord,list);
        						visitedLevel.add(transWord);
        						queue.offer(transWord);
        					}else {
        						List<String> list = graph.get(transWord);
        						list.add(curr);
        						graph.put(transWord,list);
        					}
        				}
        			}
        			charsCurr[i] = tmp;
        		}
        	}
        	dict.removeAll(visitedLevel);
        	if(flag) {
        		List<String> path = new LinkedList<>();
        		path.add(endWord);
        		findAllPath(endWord, beginWord, path, res, graph);
        		return res;
        	}	
       }
       return res; 
    }
    private void findAllPath(String curr, String end, List<String> path, List<List<String>> res, Map<String, List<String>> graph) {
    	if(curr.equals(end)) {
    		List<String> copy = new LinkedList<>(path);
    		res.add(copy);
    		return;
    	}
    	List<String> neis = graph.get(curr);
    	for(String nei: neis) {
    		path.add(0,nei);
    		findAllPath(nei,end,path,res,graph);
    		path.remove(0);
    	}
    }
    
    // two direction
    // the queue is replaced by HashSet. HashSet could o(1) find if word exists in the other set
    // one set and one graph for beginword to endword direction. one set and one graph for endword to beginword direction
    // dfs starts from the word existing in beginset and endset
    // use boolean flag to mark the current direction. 
    private String sWord = null, fWord = null;
    private HashMap<String, List<String>> bGraph = null, eGraph = null;
    public List<List<String>> findLaddersTwo(String beginWord, String endWord, List<String> wordList) {
        if(beginWord == null || endWord == null || wordList == null) return new ArrayList<>();
        Set<String> dict = new HashSet<>();
        for(String word: wordList) {
        	dict.add(word);
        }
        // corner case 
        if(!dict.contains(endWord)) return new ArrayList<>();
        Set<String> bSet = new HashSet<>();
        Set<String> eSet = new HashSet<>();
        bSet.add(beginWord);
        eSet.add(endWord);
        sWord = beginWord;
        fWord = endWord;
        bGraph = new HashMap<>();
        eGraph = new HashMap<>();
        boolean flag = false;
        List<List<String>> res = new ArrayList<>();
        while(!eSet.isEmpty() && !bSet.isEmpty()) {
        	Map<String,List<String>> graph = bGraph;
        	Set<String> set1= bSet;
        	Set<String> set2= eSet;
        	boolean beginToEnd = true;
        	if(bSet.size() > eSet.size()) {
        		set1 = eSet;
        		set2 = bSet;
        		graph = eGraph;
        		beginToEnd = false;
        	}
        	
        	Set<String> nextLevel= new HashSet<>();
        	Set<String> sourceLevel = new HashSet<>();
        	for(String curr: set1) {
        		char[] charsCurr = curr.toCharArray();
        		for(int i = 0; i < charsCurr.length; i++) {
        			char tmp = charsCurr[i];
        			for(char ch = 'a'; ch <= 'z'; ch++) {
        				charsCurr[i] = ch;
        				String transWord = String.valueOf(charsCurr);
        			
        				if(ch != tmp && (dict.contains(transWord) || set2.contains(transWord))) {
        					if(set2.contains(transWord)) { 
        						sourceLevel.add(transWord);
        						flag = true;
        					}
        					if(!nextLevel.contains(transWord)) {
        						nextLevel.add(transWord);
        					}
        					if(!graph.containsKey(transWord)) {
        						graph.put(transWord,new ArrayList<>());
        					}
        					List<String> nextList = graph.get(transWord);
        					nextList.add(curr);
        					graph.put(transWord, nextList);
        				}
        			}
        			charsCurr[i] = tmp;
        		}
        	}
        	if(beginToEnd) {
        		bSet = nextLevel;
        	}else {
        		eSet = nextLevel;
        	}

        	dict.removeAll(nextLevel);
        	
        	if(flag) {
        		for(String source: sourceLevel) {
        			List<String> path = new LinkedList<>();
        			path.add(source);
        			findAllPathTwo(source, source, path, res, true);
        		}
        		return res;
        		
        	}	
       }
       return res; 
    }
    private void findAllPathTwo(String curr, String source, List<String> path, List<List<String>> res, boolean beginToEnd) {
    	
    	if(curr.equals(fWord) && beginToEnd) {
    		findAllPathTwo(source,source,path,res,false);
    	}else if(curr.equals(sWord) && !beginToEnd) {
    		res.add(new ArrayList<>(path));
    	}else {
    		List<String> nextList = beginToEnd ? eGraph.get(curr) : bGraph.get(curr);
    		for(String next: nextList) {
    			if(beginToEnd) {
    				path.add(next);
    				findAllPathTwo(next,source,path,res,beginToEnd);
    				path.remove(path.size() - 1);
    			}else {
    				path.add(0,next);
    				findAllPathTwo(next,source,path,res,beginToEnd);
    				path.remove(0);
    			}
    		}
    	}
    }
	public static void main(String[] args) {
		LeetCode126WordLadderTypeTwo test = new LeetCode126WordLadderTypeTwo();
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
//		List<List<String>> res = test.findLadders(beginWord, endWord, wordList);
		List<List<String>> resTwo = test.findLaddersTwo(beginWord, endWord, wordList);
//		for(List<String> list: res) {
//			for(String str: list) {
//				System.out.print(str + " ");
//			}
//			System.out.println();
//		}
		for(List<String> list: resTwo) {
			for(String str: list) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
		// TODO Auto-generated method stub

	}

}
