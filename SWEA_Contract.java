package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_Contract {
	static int N;
	static int start;
	static List<Integer>[] list;
	static int maxDept;
	static int bestNode;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=10;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			int maxNode = Integer.MIN_VALUE;
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				if(!st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
				arr[i] = Integer.parseInt(st.nextToken());
				maxNode = Math.max(maxNode, arr[i]);
			}
			
			list = new ArrayList[maxNode+1];
			visited = new boolean[maxNode+1];
			
			for(int i=0;i<=maxNode;i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0;i<N;i+=2) {
				list[arr[i]].add(arr[i+1]);
			}
			maxDept = 0;
			bestNode = -1;
			bfs(start);
			sb.append("#"+t+" "+bestNode+"\n");
		}
		System.out.println(sb.toString());
		
	}
	static void bfs(int node) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {node,0});
		visited[node] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int currNode = curr[0];
			int dept = curr[1];
			
			if(maxDept<dept) {
				bestNode = currNode;
				maxDept = dept;
			}else if(maxDept==dept) {
				if(bestNode<currNode) {
					bestNode = currNode;
				}
			}
			for(int num : list[currNode]) {
				if(visited[num]) continue;
				visited[num] = true;
				q.offer(new int[] {num,dept+1});
			}
		}
		
		
	}
}
