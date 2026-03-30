package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1211_Ladder2_D4 {
	static int[][] graph;
	static int best;
	static int[] dr = {0,0,1};
	static int[] dc = {1,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=10;t++) {
			graph = new int[100][100];
			List<Integer> list = new ArrayList<>();
			int test_case = Integer.parseInt(br.readLine());
			for(int r=0;r<100;r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0;c<100;c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					if(r==0 && graph[r][c]==1) {
						list.add(c);
					}
				}
			}
			best = Integer.MAX_VALUE;
			int index=0;
			for(int startC : list) {
				int cnt = find(0,startC);
				if (best>=cnt) { //best가 더 크면 갱신하기
					best = cnt;
					index = startC;
				}
				restore();
			}
			sb.append("#"+t).append(" ").append(index).append("\n");
		}
		System.out.println(sb.toString());
	}
	static int find(int row,int col) {
		int cnt=1;
		int nr = 0;
		int nc = 0;
		
		while(true) {
			graph[row][col] = 9;
			for(int d=0;d<3;d++) {
				nr = row+dr[d];
				nc = col + dc[d];
				if(nr<0 || nc<0 || nr>=100 || nc>=100) continue;
				if(graph[nr][nc]==1) break;
			}
			if(nr==99) return cnt;
			cnt++;
			row = nr;
			col = nc;
		}
	}
	static void restore() {
		for(int r=0;r<100;r++) {
			for(int c=0;c<100;c++) {
				if(graph[r][c]==9) {
					graph[r][c]=1;
				}
			}
		}
	}
}
