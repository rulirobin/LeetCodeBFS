package LeetCodeBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode77Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());
        
        int level = 0;
        while(level< k) {
        	int size = res.size();
        	while(size -- > 0) {
        		List<Integer> curr = res.remove(0);
        		for(int i = curr.size() == 0 ? 1 : curr.get(level - 1) + 1; i <= n ; i ++) {
        			List<Integer> list = new ArrayList<>(curr);
        			list.add(i);
        			res.add(list);
        		}
        	}
        	level++;
        }
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeetCode77Combinations test = new LeetCode77Combinations();
		List<List<Integer>> res = test.combine(4, 3);
		for(List<Integer> list : res) {
			for(Integer num : list) {
				System.out.print(num);
			}
			System.out.println();
		}
	}

}
