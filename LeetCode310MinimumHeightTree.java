import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LeetCode310MinimumHeightTree {
	private class TreeNode{
		public int val;
		public Set<Integer> neis;
		public TreeNode(int val){
			this.val = val;
			neis = new HashSet<>();
		}
		public void addNei(Integer nei) {
			neis.add(nei);
		}
		public void deleteNei(Integer nei) {
			neis.remove(nei);
		}
		public boolean isleaf() {
			return neis.size() == 1;
		}
	}
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    	List<Integer> res = new ArrayList<>();
        if(n <=0 || edges == null || edges.length >= n) return res; 
        if(n == 1) {
        	res.add(0);
        	return res;
        }
        int nodepool = n;
        TreeNode tree[] = new TreeNode[nodepool];
        for(int i = 0; i<n; i++) {
        	tree[i] = new TreeNode(i);
        }
        for(int[] edge : edges) {
        	tree[edge[0]].addNei(edge[1]);
        	tree[edge[1]].addNei(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
        	if(tree[i].isleaf()) {
        		queue.offer(i);
        		nodepool --;
        	}
        }
        while(nodepool > 0) {
        	int size = queue.size();
        	while(size -- > 0) {
        		int curr = queue.poll();
        		for(Integer nei: tree[curr].neis) {
        			tree[nei].deleteNei(curr);
        			if(tree[nei].isleaf()) {
        				queue.offer(nei);
        				nodepool --;
        			}
        		}
        	}
        }
        for(Integer node: queue) {
        	res.add(node);
        }
        return res;
        
    
    }
	
	
	public static void main(String[] args) {
		int[][] edges = {{1,0},{1,2},{1,3}};
		LeetCode310MinimumHeightTree test = new LeetCode310MinimumHeightTree();
		List<Integer> res = test.findMinHeightTrees(4, edges);
		for(Integer num: res) {
			System.out.println(num);
		}
	} 
}
