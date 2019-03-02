package LeetCodeBFS;

import java.util.Comparator;
import java.util.PriorityQueue;

//BFS + Minheap 
public class LeetCode407TrappingRainWaterTwo {
	private class Cell{
		public int i, j,height;
		public Cell(int i, int j, int height) {
			this.i = i;
			this.j = j;
			this.height = height;
		}
	}
    public int trapRainWater(int[][] heightMap) {
        int res =0;
        if(heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) return res;
        // use minheap to sort the input. pop the lowest bar everytime.
        PriorityQueue<Cell> pQueue = new PriorityQueue<Cell>(1,new Comparator<Cell>(){
        	public int compare(Cell a, Cell b) {
        		return a.height - b.height;
        	}
        });
        // add boarders into queue
        int row = heightMap.length;
        int col = heightMap[0].length;
        boolean[][] visited = new boolean[row][col];
        for(int i = 0; i <row; i++) {
        	pQueue.offer(new Cell(i,0,heightMap[i][0]));
        	pQueue.offer(new Cell(i,col - 1, heightMap[i][col-1]));
        	visited[i][0] = true;
        	visited[i][col-1] = true;
        }
        for(int j = 1; j <col - 1; j++) {
        	pQueue.offer(new Cell(0,j,heightMap[0][j]));
        	pQueue.offer(new Cell(row - 1,j, heightMap[row - 1][j]));
        	visited[0][j] = true;
        	visited[row-1][j] = true;
        }
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!pQueue.isEmpty()) {
        	Cell curr = pQueue.poll();
        	for(int[] dir: dirs) {
        		int i = dir[0] + curr.i;
        		int j = dir[1] + curr.j;
        		Cell nei = new Cell(i,j,heightMap[i][j]);
        		if(i >= 0 && i < row && j >= 0 && j < col && !visited[i][j]) {
        			visited[i][j] = true;
        			res += Math.max(0, curr.height - nei.height);
        			pQueue.offer(new Cell(i,j,Math.max(nei.height, curr.height)));
        		}
        	}
        }
        
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
