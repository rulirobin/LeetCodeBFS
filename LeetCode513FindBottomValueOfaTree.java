package LeetCodeBFS;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode513FindBottomValueOfaTree {
	 public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> pQueue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	pQueue = new LinkedList<>(queue);
        	int size = queue.size();
        	while(size -- > 0) {
        		TreeNode curr = queue.poll();
        		if(curr.left != null) {
        			queue.offer(curr.left);
        		}
        		if(curr.right != null) {
        			queue.offer(curr.right);
        		}
        	}
        }
        return pQueue.peek().val;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
