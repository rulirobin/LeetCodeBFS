package LeetCodeBFS;

import java.util.ArrayList;
import java.util.List;

public class LeetCode78Subnets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0,new ArrayList<>(), nums, res);
        return res;
    }
    private void dfs(int index, List<Integer> path,int[] nums,  List<List<Integer>> res) {
    	res.add(new ArrayList<>(path));
    	for(int i = index; i< nums.length; i++) {
    		path.add(nums[i]);
    		dfs(i+1,path,nums,res);
    		path.remove(path.size() - 1);
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeetCode78Subnets test = new LeetCode78Subnets();
		int[] nums = {1,2,3};
		List<List<Integer>> res = test.subsets(nums);
		for(List<Integer> list : res) {
			for(Integer num : list) {
				System.out.print(num);
			}
			System.out.println();
		}
	}

}
