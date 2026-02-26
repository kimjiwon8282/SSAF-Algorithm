package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_몬스터헌터_D4 {
	static int N;
	static int[][] graph;
	static Map<Integer,Node> nodes;
	static boolean[] monsterVisited;
	static boolean[] userVisited;
	static int best;
	
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<=T;t++) {
			nodes = new HashMap<>();
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			for(int r=0;r<N;r++) {
				StringTokenizer st =new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					if(graph[r][c]!=0) {
						nodes.put(graph[r][c], new Node(r,c));
					}
				}
			}
			int size = nodes.size();
			monsterVisited=new boolean[size/2];
			userVisited = new boolean[size/2];
			
			best = Integer.MAX_VALUE;
			
			dfs(0,0,0,0);
			sb.append("#"+t+" "+best+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(int idx, int currentDist, int cr,int cc) {
		if(currentDist>=best) {
			return;
		}
		if(idx==nodes.size()) {
			best = Math.min(best, currentDist);
			return;
		}
		for(Integer n : nodes.keySet()) {
			if(n<0) { //고객이라면
				if(userVisited[(n*-1)-1]) continue;
				if(!monsterVisited[(n*-1)-1]) continue;//몬스터 방문 안했다면
				//방문 했다면
				userVisited[(n*-1)-1] = true;
				Node temp = nodes.get(n);
				int dist = Math.abs(cr-temp.r) + Math.abs(cc-temp.c);
				dfs(idx+1,currentDist+dist,temp.r,temp.c);
				userVisited[(n*-1)-1] = false;
			}
			if(n>0) {//몬스터면 
				if(monsterVisited[n-1]) continue;
				monsterVisited[n-1] = true;
				Node temp = nodes.get(n);
				int dist = Math.abs(cr-temp.r) + Math.abs(cc-temp.c);
				dfs(idx+1,currentDist+dist,temp.r,temp.c);
				monsterVisited[n-1] = false;
			}
		}
	}
}
