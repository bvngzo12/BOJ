import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		TreeSet<Integer> s1 = new TreeSet<>();
		TreeSet<Integer> s2 = new TreeSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			s1.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			s2.add(Integer.parseInt(st.nextToken()));
		}
		
		s1.removeAll(s2);
		StringBuilder sb = new StringBuilder();
		
		System.out.println(s1.size());
		while(!s1.isEmpty()) {
			sb.append(s1.pollFirst()).append(" ");
		}
		
		System.out.println(sb.toString());
		
	}

}
