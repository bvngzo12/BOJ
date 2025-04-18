import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			
			switch(cmd){
			case "push_front":
				ad.offerFirst(Integer.parseInt(st.nextToken()));
				break;
			case "push_back":
				ad.offerLast(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front":
				sb.append(ad.isEmpty() ? -1 : ad.pollFirst()).append("\n");
				break;	
			case "pop_back":
				sb.append(ad.isEmpty() ? -1 : ad.pollLast()).append("\n");
				break;	
			case "size":
				sb.append(ad.size()).append("\n");
				break;	
			case "empty":
				sb.append(ad.isEmpty() ? 1 : 0).append("\n");
				break;	
			case "front":
				sb.append(ad.isEmpty() ? -1 : ad.peekFirst()).append("\n");
				break;	
			case "back":
				sb.append(ad.isEmpty() ? -1 : ad.peekLast()).append("\n");
				break;
			}
			
		}
		System.out.println(sb.toString());
	}

}
