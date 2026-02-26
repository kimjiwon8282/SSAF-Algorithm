package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준G2_문제집_1766 {
	static int N;
	static int M;
	static List<Integer>[] list;
	static int[] inDegree;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegree = new int[N+1];
		list = new ArrayList[N+1];
		
		for(int n=0;n<=N;n++) {
			list[n] = new ArrayList<>();
		}
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			list[n1].add(n2);
			inDegree[n2]++;
		}
		
		topoSort();
		System.out.println(sb.toString());
	}
	static void topoSort() {
		Queue<Integer> q = new PriorityQueue<>();
		for(int i=1;i<=N;i++) {
			if(inDegree[i]==0) q.offer(i);
		}
		while(!q.isEmpty()) {
			int curr = q.poll();
			sb.append(curr).append(" ");
			
			for(int next: list[curr]) {
				inDegree[next]--;
				if(inDegree[next]==0) q.offer(next);
			}
		}
	}

}
