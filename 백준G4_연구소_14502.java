package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준G4_연구소_14502 {
	static int N,M;
	static int[][] graph;
	static class Virus{
		int r;
		int c;
		public Virus(int r,int c) {
			super();
			this.r=r;
			this.c=c;
		}
	}
	static List<Virus> list;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	static int best;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		list = new ArrayList<>();
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<M;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
				if(graph[r][c]==2) {
					list.add(new Virus(r,c));
				}
			}
		}
		best = -1;
		dfs(0,0,0);
		
		System.out.println(best);
	}
	static void dfs(int idx,int startR,int startC) {
		if(idx==3) {
			//바이러스 확산
			diffusion();
			//개수 세기
			int currCount = count();
			//개수 갱신
			best = Math.max(best, currCount);
			//확산 복구하기
			restore();
			return;
		}
		for(int r = startR;r<N;r++) {
			int cIdx = (r==startR)? startC : 0;
			for(int c =cIdx;c<M;c++) {
				if(graph[r][c]==0) {
					graph[r][c]=1;
					dfs(idx+1,r,c+1);
					graph[r][c]=0;
				}
			}
		}
	}
	static void diffusion() {
		for(Virus v : list) { //graph[r][c]==2인곳.
			int startR = v.r;
			int startC = v.c;
			
			Queue<Virus> q = new ArrayDeque<>();
			q.offer(new Virus(startR,startC));
			
			while(!q.isEmpty()) {
				Virus curr = q.poll();
				for(int d=0;d<4;d++) {
					int nr = curr.r+dr[d];
					int nc = curr.c+dc[d];
					if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
					if(graph[nr][nc]==0) {//빈공간이면
						graph[nr][nc]=2;
						q.offer(new Virus(nr,nc));
					}
				}
			}
		}
	}
	
	static int count() {
		int cnt=0;
		for(int r=0;r<N;r++) {
			for(int c=0;c<M;c++) {
				if(graph[r][c]==0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	static void restore() {
		for(int r=0;r<N;r++) {
			for(int c=0;c<M;c++) {
				if(graph[r][c]==2) {
					graph[r][c]=0;
				}
			}
		}
		for(Virus v : list) {
			graph[v.r][v.c] = 2;
		}
	}
}
