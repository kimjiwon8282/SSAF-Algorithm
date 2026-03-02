package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ¹éÁØS3_N°úM_15649 {
	static int N,M;
	static int[] result;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new int[M];
		visited = new boolean[N+1];
		permutation(0);
		System.out.println(sb.toString());
	}
	static void permutation(int idx) {
		if(idx==M) {
			for(int num : result) {
				sb.append(num+" ");
			}
			return;
		}
		for(int i=1;i<=N;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			result[idx] = i;
			permutation(idx+1);
			visited[i] = false;
		}
	}

}
