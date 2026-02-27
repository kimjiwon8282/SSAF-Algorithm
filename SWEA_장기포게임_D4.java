package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_장기포게임_D4 {
	static int N;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			visited = new boolean[N][N];
			int sr=-1;
			int sc= 1;
			
			for(int r=0;r<N;r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					if(graph[r][c]==2) {
						sr = r;
						sc = c;
						graph[r][c] = 0; 
					}
				}
			}
			result = 0;
			dfs(0,sr,sc);
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					if(visited[r][c]) result++;
				}
			}
			sb.append("#"+t+" "+result+"\n");
		}
		System.out.println(sb.toString());
	}
	static void dfs(int idx,int cr,int cc) {
		if(idx==3) {
			return;
		}

		for(int d=0;d<4;d++) { //4방향으로 이동하기
			//현재 위치에서 잡을 수 있는 알 구하기
			int nr = cr;
			int nc = cc;
			boolean foundJumpPiece = false;
			while(true) {
				nr += dr[d];
				nc += dc[d];
				
				if(nr>=N || nc>=N || nr<0 || nc<0) { //벗어나면
					break;
				}
				
				if(!foundJumpPiece) {
					if(graph[nr][nc]==1) {
						foundJumpPiece = true; //찾음
					}
				}else {
					if(graph[nr][nc]==1) {
						visited[nr][nc] = true;
						graph[nr][nc] = 0;
						dfs(idx+1,nr,nc);
						graph[nr][nc] = 1;
						break;
					}else if(graph[nr][nc]==0){
						dfs(idx+1,nr,nc);
					}
				}
			}
		}
	}
}
