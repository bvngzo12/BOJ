import java.util.*;


class Solution {
    public int solution(int n, int[][] costs) {
        int ans = 0;
        ArrayList<Vertex>[] adjList = new ArrayList[n];
        
        for(int i = 0; i < n; i++){
            adjList[i] = new ArrayList<Vertex>();
        }
        
        for(int[] cost : costs){
            int start = cost[0];
            int end = cost[1];
            int weight = cost[2];
            
            adjList[start].add(new Vertex(end, weight));
            adjList[end].add(new Vertex(start, weight));
        }
        
        boolean[] selected = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        
        PriorityQueue<Vertex> PQ = new PriorityQueue();
        PQ.offer(new Vertex(0,0));
        dist[0] = 0;
        
        while(!PQ.isEmpty()){
            Vertex curr = PQ.poll();
            
            if(selected[curr.e]) continue;
            
            selected[curr.e] = true;
            System.out.println("curr : "+ curr.e + " weight : "+curr.weight);
            ans += dist[curr.e];
            
            for(Vertex next : adjList[curr.e]){
                if(!selected[next.e] && dist[next.e] > next.weight){    
                    dist[next.e] = next.weight;
                    PQ.offer(next);
                    
                }
            }
            
        }
        
        
        return ans;
    }
    
    class Vertex implements Comparable<Vertex>{
        int e;
        int weight;
    
        Vertex(int e, int w){
            this.e = e;
            this.weight = w;
        }
    
        public int compareTo(Vertex o1){
            return Integer.compare(this.weight, o1.weight);
        } 
    
        public String toString(){
            return "end : "+this.e+" weight : "+this.weight;
        }
        
    }
    
}