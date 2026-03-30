package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_14889_스타트와링크 {
	static int[][] graph;
	static int N;
	static List<Integer> result;
	static List<Integer> remain;
	static boolean[] visited;
	static int best;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int r=0;r<N;r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		result = new ArrayList<>();
		remain = new ArrayList<>();
		visited = new boolean[N];
		best = Integer.MAX_VALUE;
		combination(0,0);
		System.out.println(best);
		
	}
	
	static void combination(int idx,int start) {
		if(best == 0) return;
		if(idx==N/2) {
			int selCnt=0;
			int nSelCnt = 0;
			//visited배열 가지고 팀 인원 검사
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(r==c) continue;
					if(visited[r] && visited[c]) {
						selCnt += graph[r][c];
					}
					else if(!visited[r]&&!visited[c]) {
						nSelCnt+=graph[r][c];
					}
				}
			}
			int temp = Math.abs(selCnt-nSelCnt);
			best = Math.min(best, temp);
			return;
		}
		for(int i=start;i<N;i++) {
			visited[i] = true;
			combination(idx+1,i+1);
			visited[i] = false;
		}
	}

}
