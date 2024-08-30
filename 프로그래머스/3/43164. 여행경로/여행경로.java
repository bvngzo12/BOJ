import java.util.*;

class Solution {
    
    boolean[] visited;
    String[] answer;
    int n;
    
    public String[] solution(String[][] tickets) {
        n = tickets.length;
        
        answer = new String[n+1];
        visited = new boolean[n];
        
        answer[0] = "ICN";
        
        Arrays.sort(tickets, (o1,o2)->(o1[0].equals(o2[0]) ? o1[1].compareTo(o2[1]) : o1[0].compareTo(o2[0])));
        
        dfs(1, tickets);
        
        
        return answer;
    
    
    }
    
    public boolean dfs(int idx, String[][]tickets){
        if(idx == answer.length){
            return true;
        }
        
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
           
            if(answer[idx-1].equals(tickets[i][0])){
                answer[idx] = tickets[i][1];
                visited[i] = true;
                if(dfs(idx+1,tickets))return true;
                visited[i] = false;
            }
        }
        
        return false;
    }
    
    
}