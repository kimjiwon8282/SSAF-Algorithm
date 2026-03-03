package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_토마토_7576 {
	static int M;
	static int N;
	static int[][] graph;
	static Queue<Tomato> q= new ArrayDeque<>();
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0}; 
	static int resultTime;
	
	static StringBuilder sb = new StringBuilder();
	
	static class Tomato{
		int r;
		int c;
		int time;
		public Tomato(int r, int c,int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				graph[n][m] = Integer.parseInt(st.nextToken());
				if(graph[n][m]==1) {
					q.offer(new Tomato(n,m,0));
				}
			}
		}
		bfs();
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			Tomato curr = q.poll();
			resultTime = curr.time;
			
			for(int d=0;d<4;d++) {
				int nr = curr.r+dr[d];
				int nc = curr.c+dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue; //나가면
				if(graph[nr][nc]==0) {//안익은 토마토
					graph[nr][nc]=1;//익히기
					q.offer(new Tomato(nr,nc,curr.time+1));
				}
			}
		}
		for(int r=0;r<N;r++) {
			for(int c=0;c<M;c++) {
				if(graph[r][c]==0) {
					sb.append(-1);
					return;
				}
			}
		}
		sb.append(resultTime);
		
	}

}
