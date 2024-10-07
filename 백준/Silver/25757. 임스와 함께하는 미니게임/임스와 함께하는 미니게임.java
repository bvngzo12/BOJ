import java.io.*;
import java.util.*;

public class Main {
	static int N, player;
	static Map<String, Integer> maps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		String s = st.nextToken();
		if (s.equals("Y")) player = 1;
		else if (s.equals("F")) player = 2;
		else player = 3;
		
		maps = new HashMap<>();
		
		int curPlayer = 0;
		int totalPlayer = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine().split(" ")[0];
			
			if (maps.containsKey(input)) continue;
			else {
				maps.put(input, 1);
				
				curPlayer++;
				
				if (curPlayer == player) {
					totalPlayer++;
					curPlayer = 0;
				}
				
			}
		}
		
		bw.write(totalPlayer + "\n");
		
		br.close();
		bw.close();
	}

}
