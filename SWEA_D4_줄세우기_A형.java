package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_줄세우기_A형 {
	static int N;
	static int M;
	static List<Integer>[] list;
	static int[] inDegree;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#"+t+" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list = new ArrayList[N+1];
			inDegree = new int[N+1];
			
			for(int i=0;i<=N;i++) {
				list[i] = new ArrayList<>();
			}
			for(int m=0;m<M;m++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				list[n1].add(n2);
				inDegree[n2]++;
			}
			topoSort();
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static void topoSort() {
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1;i<=N;i++) {
			if(inDegree[i]==0) {
				q.offer(i);
			}
		}
		while(!q.isEmpty()) {
			int curr = q.poll();
			sb.append(curr).append(" ");
			for(int num : list[curr]) {
				inDegree[num]--;
				if(inDegree[num]==0) q.offer(num);
			}
		}
	}
}
