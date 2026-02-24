package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈7733_도둑_BFS {
	static int N;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dc = {0,0,1,-1};
	static int[] dr = {1,-1,0,0};
	static int best;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			for(int r=0;r<N;r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			best = 1;
			for(int day=1;day<=100;day++) {
				visited = new boolean[N][N];
				for(int r=0;r<N;r++) {
					for(int c=0;c<N;c++) {
						if(graph[r][c]==day) {
							graph[r][c] = -1;
						}
					}
				}
				
				int count=0;
				for(int r=0;r<N;r++) {
					for(int c=0;c<N;c++) {
						if(!(graph[r][c]==-1) && !visited[r][c]){
							visited[r][c] = true;
							bfs(r,c);
							count++;
						}
					}
				}
				best = Math.max(count, best);
			}
			sb.append("#"+t+" "+best+"\n");
		}
		System.out.println(sb.toString());
	}
	static void bfs(int r,int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cr = curr[0];
			int cc = curr[1];
			
			for(int d=0;d<4;d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(graph[nr][nc]==-1 || visited[nr][nc]) continue;
				q.offer(new int[] {nr,nc});
				visited[nr][nc] = true;
			}
		}
	}
	

}