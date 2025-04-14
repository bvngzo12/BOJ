import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] liquids = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0;
		int e = n-1;
		
		int curr = Integer.MAX_VALUE; 
		int[] ans = new int[2];
		
		int target = 0;
		
		while(s < e) {
			
			int temp = liquids[s] + liquids[e];
			
			if(Math.abs(target - temp) < Math.abs(target - curr)) {
				curr = temp;
				ans[0] = s;
				ans[1] = e;
			}
			
			if(temp < target) {
				s++;
			}else {
				e--;
			}
			
		}
		
		System.out.println(liquids[ans[0]]+" "+liquids[ans[1]]);
		
	}

}
