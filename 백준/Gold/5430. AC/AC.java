import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			char[] cmd = br.readLine().toCharArray();

			int n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			
			String[] arr = str.length() >= 3 ? str.substring(1, str.length()-1).split(",")
											: new String[0];

			ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
			
			for(int i = 0; i < arr.length; i++) {
				ad.offer(Integer.parseInt(arr[i]));
			}

			boolean reverse = false;
			boolean flag = true;
			StringBuilder sb = new StringBuilder();
			
			for(int c = 0; c < cmd.length; c++) {
				if(cmd[c] == 'R') {
					reverse = !reverse;
				}
				else
				{
					if(ad.isEmpty()) {
						sb.append("error");
						flag = false;
						break;
					}
					
					if(reverse) {
						ad.pollLast();
					}else {
						ad.pollFirst();
					}
					
				}
			}
			
			if(flag) {
				sb.append("[");
				while(!ad.isEmpty()) {
					if(reverse) {
						sb.append(ad.pollLast());
					}else {
						sb.append(ad.pollFirst());
					}
					
					if(!ad.isEmpty()) sb.append(",");
				}
				
				sb.append("]");
			}
			
			System.out.println(sb.toString());
			
		}
		
		
	}

}
