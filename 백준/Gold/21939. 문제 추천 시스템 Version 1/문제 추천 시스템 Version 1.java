
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	static class Problem implements Comparable<Problem>{
		int lv;
		int no;
		
		public Problem(int no, int lv) {
			super();
			this.lv = lv;
			this.no = no;
		}

		@Override
		public int compareTo(Problem o) {
			return (this.lv == o.lv) ? -Integer.compare(this.no, o.no) : -Integer.compare(this.lv, o.lv);
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		TreeSet<Problem> probs = new TreeSet();
		HashMap<Integer, Integer> map = new HashMap();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Problem p = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			probs.add(p);
			map.put(p.no, p.lv);
		}
		
		StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			switch(st.nextToken()){
				
			case "add":
				Problem p = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				probs.add(p);
				map.put(p.no, p.lv);
				break;
			
			case "recommend":
				int cmd = Integer.parseInt(st.nextToken());
				if(cmd == 1) {
					sb.append(probs.first().no).append("\n");
				}else {
					sb.append(probs.last().no).append("\n");
				}
				break;
			
			case "solved":
				int pNo = Integer.parseInt(st.nextToken());
				probs.remove(new Problem(pNo, map.get(pNo)));
				map.remove(pNo);
				break;
			}
		}
		System.out.println(sb.toString());
	}

}
