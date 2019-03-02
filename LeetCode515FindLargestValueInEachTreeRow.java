package LeetCodeBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCodeBFS.LeetCode513FindBottomValueOfaTree.TreeNode;

public class LeetCode515FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	int levelMax = Integer.MIN_VALUE;
        	int size = queue.size();
        	while(size -- > 0) {
        		TreeNode curr = queue.poll();
        		levelMax = Math.max(levelMax, curr.val);
        		if(curr.left != null) {
        			queue.offer(curr.left);
        		}
        		if(curr.right != null) {
        			queue.offer(curr.right);
        		}
        	}
        	res.add(levelMax);
        }
        return res;
    }
}
