package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_키순서_A형 {
	static int N;
	static int M;
	static List<Integer>[] asc;
	static List<Integer>[] desc;
	static int totalCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			asc = new ArrayList[N+1];
			desc = new ArrayList[N+1];
			
			for(int i=0;i<=N;i++) {
				asc[i] = new ArrayList<>();
				desc[i] = new ArrayList<>();
			}
			
			for(int m=0;m<M;m++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				asc[num1].add(num2);
				desc[num2].add(num1);
			}
			
			totalCnt=0;
			for(int n=1;n<=N;n++) {
				int ascNum = checkAsc(n);
				int descNum = checkDesc(n);
				if((ascNum + descNum) == N-1) {
					totalCnt++;
				}
			}
			sb.append("#"+t+" "+totalCnt+"\n");
		}
		System.out.println(sb.toString());
		
	}
	static int checkAsc(int n) {
		boolean[] visited = new boolean[N];
		visited[n-1] = true;
		Queue<Integer> q= new ArrayDeque<>();
		int cnt=0;
		q.offer(n);
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int next:asc[curr]) {
				if(visited[next-1]) continue;
				q.offer(next);
				visited[next-1] = true;
				cnt++;
			}
		}
		return cnt;
	}
	static int checkDesc(int n) {
		boolean[] visited = new boolean[N];
		visited[n-1] = true;
		Queue<Integer> q= new ArrayDeque<>();
		int cnt=0;
		q.offer(n);
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int next:desc[curr]) {
				if(visited[next-1]) continue;
				q.offer(next);
				visited[next-1] = true;
				cnt++;
			}
		}
		return cnt;
	}

}
