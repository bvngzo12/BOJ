import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Queue<Integer> knowTruth = new ArrayDeque<Integer>();
		
		int t = Integer.parseInt(st.nextToken());
		for(int i = 0; i < t; i++) {
			int trueMan = Integer.parseInt(st.nextToken());
			knowTruth.offer(trueMan);
		}

		// 파티 배열
		ArrayList<Integer>[] parties = new ArrayList[M+1];		//파이에 누가 참여했는지
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			parties[i] = new ArrayList<Integer>();
			int numOfAttend = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < numOfAttend; j++) {
				int attendee = Integer.parseInt(st.nextToken());
				parties[i].add(attendee);
			}
		}
		
		ArrayList<Integer>[] attendees = new ArrayList[N+1];	//누가 어떤 파티에 참여했는지
		for(int i = 1; i <= N; i++) {
			attendees[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= M; i++) {
			for(int attendee : parties[i]) {
				attendees[attendee].add(i);
			}
		}
		
		boolean[] trueParty = new boolean[M+1];
		boolean[] iKnowTrue = new boolean[N+1];
		int ans = M;
		
		while(!knowTruth.isEmpty()) {
			int trueMan = knowTruth.poll();
			iKnowTrue[trueMan] = true;
			
			for(int partyNo : attendees[trueMan]) {
				if(trueParty[partyNo]) continue;
				
				trueParty[partyNo] = true;
				ans--;
				
				for(int trueAttend : parties[partyNo]) {
					if(!iKnowTrue[trueAttend]) {
						knowTruth.offer(trueAttend);
					}
				}
			}
		}
		
		System.out.println(ans);
	}

}
