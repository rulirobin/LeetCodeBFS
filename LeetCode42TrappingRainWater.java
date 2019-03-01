package bfs;

import java.util.Stack;

public class LeetCode42TrappingRainWater {
    public int trap(int[] height) {
    	int res = 0;
    	int bar = 0;
        if(height == null || height.length == 0) return res;
        Stack<Integer> stack = new Stack<>();
        int index = 0; 
        while(index < height.length) {
        	if(stack.size() == 0 || height[stack.peek()] >= height[index]) {
        		stack.push(index++);
        	}else {
        		int prev = stack.pop();
        		bar = stack.isEmpty() ? 0 : (Math.min(height[index], height[stack.peek()]) - height[prev]) * (index - stack.peek() - 1);
        		res += bar;
        	}
        }
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		LeetCode42TrappingRainWater test = new LeetCode42TrappingRainWater();
		System.out.println(test.trap(height));
	}

}
