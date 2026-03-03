package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D2_1979 {
	static int N;
	static int K;
	static int[][] graph;
	
	static int[] dr = {0,1}; //0번 : 오른쪽 , 1번 : 아래
	static int[] dc = {1,0}; 
	
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			sb.append("#"+t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			graph = new int[N][N];
			visited = new boolean[N][N];
			
			for(int r = 0;r<N;r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			int rCount=0;
			//오른쪽 검사
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(visited[r][c]==true) continue;
					if(graph[r][c]==0) continue;
					if(rCheck(r,c)) { //1이면서 방문하지 않았을때
						rCount++;
					}
					
				}
			}
			for(int n=0;n<N;n++) {
				Arrays.fill(visited[n], false);
			}
			
			int dCount = 0;
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(visited[r][c]==true) continue;
					if(graph[r][c]==0) continue;
					if(dCheck(r,c)) { //1이면서 방문하지 않았을때
						dCount++;
					}
					
				}
			}
			int total = rCount+dCount;
			sb.append(total+"\n");
		}
		System.out.println(sb.toString());
	}
	static boolean rCheck(int r,int c) { //1이면서 방문하지 않았을때가 조건, 오른쪽 옆은 무조건 방문안함
		int count=1;
		visited[r][c] = true;
		int nr = -1;
		int nc = -1;
		while(true) {
			if(count>K) return false;
			nr = r + dr[0];
			nc = c + dc[0];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			if(graph[nr][nc]==1) {
				visited[nr][nc] = true;
				count++;
			}else if(graph[nr][nc]==0) {
				break;
			}
			r = nr;
			c = nc;
		}
		if(count==K) {
			return true;
		}else {
			return false;
		}
	}
	static boolean dCheck(int r,int c) { //1이면서 방문하지 않았을때가 조건, 아래쪽은 무조건 방문안함
		int count=1;
		visited[r][c] = true;
		int nr = -1;
		int nc = -1;
		while(true) {
			if(count>K) return false;
			nr = r + dr[1];
			nc = c + dc[1];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			if(graph[nr][nc]==1) {
				visited[nr][nc] = true;
				count++;
			}else if(graph[nr][nc]==0) {
				break;
			}
			r = nr;
			c = nc;
		}
		if(count==K) {
			return true;
		}else {
			return false;
		}
	}

}
