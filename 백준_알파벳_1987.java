package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_알파벳_1987 {
	static int R;
	static int C;
	static char[][] graph;
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static boolean[] visited;
	static int bestDept=1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new char[R][C];
		visited = new boolean[26];
		
		for(int i=0;i<R;i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		visited[graph[0][0]-'A'] = true;
		dfs(0,0,1);
		sb.append(bestDept);
		System.out.println(sb.toString());
	}
	static void dfs(int r,int c,int dept) {
		if(bestDept<dept) {
			bestDept = dept;
		}
		
		for(int d=0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=R || nr<0 || nc>=C || nc<0) continue;
			if(visited[graph[nr][nc]-'A']) continue;
			visited[graph[nr][nc]-'A'] = true;
			dfs(nr,nc,dept+1);
			visited[graph[nr][nc]-'A'] = false;
		}
	}

}
