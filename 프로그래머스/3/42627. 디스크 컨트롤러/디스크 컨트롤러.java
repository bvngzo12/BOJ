import java.util.*;

class Solution {
    static ArrayList<int[]> list;
    static int time = 0, opTime = 0;
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        list = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            list.add(jobs[i]);
        }
        
        run();
        answer = opTime / jobs.length;
        return answer;
    }
    
    static public void run() {
        while(!list.isEmpty()) {
            int nextIdx = -1;
            int nextTime = Integer.MAX_VALUE;
            for (int i = 0; i < list.size(); i++) {
                int[] elem = list.get(i);
                
                if (elem[0] <= time) {
                    if (nextTime > elem[1]) {
                        nextIdx = i;
                        nextTime = elem[1];
                    }
                }
            }
            
            if (nextIdx < 0) {
                time++;
                continue;
            }
            
            int[] cur = list.get(nextIdx);
            
            
            list.remove(nextIdx);
            
            time += cur[1];
             
            opTime += time - cur[0];
        }
    }
}