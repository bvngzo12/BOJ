import java.util.*;

class Solution {
    static Queue<Integer> pq;
    static int time = 0;
    public int solution(int[] scoville, int K) {
        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        
        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        run(K);
        
        if (pq.poll() >= K) return time;
        
        return -1;
    }
    
    static public void run(int K) {
        while(pq.size() >= 2) {
            int elem1 = pq.poll();
            
            if (elem1 >= K ) break;
            
            int elem2 = pq.poll();
            
            int newK = elem1 + (elem2 * 2);
            
            pq.add(newK);
            
            time++;
        }
    }
}