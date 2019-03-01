package bfs;

public class LeetCode139NestedList {
    public int depthSum(List<NestedInteger> nestedList) {
        int res =0;
        if(nestedList == null || nestedList.size() == 0) return 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        int sum = 0;
        int level = 1;
        for(NestedInteger nint: nestedList){
            queue.offer(nint);
        }
        
        while(!queue.isEmpty()){
            int size = queue.size();
            int levelSum = 0;
            while(size -- > 0){
                NestedInteger curr = queue.poll();
                if(curr.isInteger()){
                    levelSum += curr.getInteger();
                }else{
                    List<NestedInteger> list = curr.getList();
                    for(NestedInteger next : list){
                        queue.offer(next);
                    }
                }
            }
            res += levelSum*level;
            levelSum = 0;
            level++;
        }
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
