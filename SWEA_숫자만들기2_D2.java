package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//25,472 kb, 72 ms
public class SWEA_숫자만들기2_D2 {

	static int N;
	static int R;
	static int[] arr;
	static int[] res;
	static boolean[] visited;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for(int t=1;t<=T;t++) {
			sb.append("#"+t+"\n");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			arr = new int[N];
			res = new int[R];
			visited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0);
			
		}
		System.out.println(sb.toString());
	}
	static void dfs(int idx) {
		if(idx==R) {
			StringBuilder num = new StringBuilder();
			for(int i=0;i<R;i++) {
				num.append(String.valueOf(res[i]));
			}
			sb.append(num.toString()+"\n");
			return;
		}
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			visited[i]=true;
			res[idx] = arr[i];
			dfs(idx+1);
			visited[i]=false;
		}
	}
}
