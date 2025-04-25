import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<int[]> meets = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			meets.add(new int[] {s,e});
		}
		
		
		meets.sort((o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));
		
		int currEnd = meets.get(0)[1];		// 가장 먼
		int cnt = 1;
		
		for(int i = 1; i < n; i++) {
			int[] nxt = meets.get(i);
			
			if(nxt[1] < currEnd) {
				currEnd = nxt[1];
			}else if(nxt[1] >= currEnd && nxt[0] >= currEnd){
				cnt++;
				currEnd = nxt[1];
			}
		}
		
		System.out.println(cnt);
	}

}
