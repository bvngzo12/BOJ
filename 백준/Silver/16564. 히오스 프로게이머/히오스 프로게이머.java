import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] lv = new int[N];
		int max = -1;
		
		for(int i = 0; i < N; i++) {
			int v = Integer.parseInt(br.readLine());
			
			lv[i] = v;
			max = Math.max(max, v);
		}
		
		int left = 1;
		int right = max + K;
		int ans = -1;
		while(left <= right) {
			int mid = (left + right)/2;
			
			boolean flag = true;
			int total = K;
			for(int i = 0; i < N; i++) {
				if(lv[i] < mid) {
					total -= (mid - lv[i]);
				}
				if(total < 0) {
					flag = false;
				}
			}
			
			if(flag) {
				ans = Math.max(ans, mid);
				left = mid +1;
			}else {
				right = mid - 1;
			}
			
		}
		System.out.println(ans);
		
	}

}
