package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_S3_18429_근손실 {
	static int N; //N일간
	static int K;
	static int[] arr;
	static boolean[] visited;
	static int total;
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		total = 0;
		perm(0,500);
		System.out.println(total);
	}
	static void perm(int idx,int currSum) {
		if(currSum<500) {
			return;
		}
		if(idx==N) { //모두 방문했으면 && 500이상일 경우
			total++;
		}
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm(idx+1,currSum-K+arr[i]);
			visited[i] = false;
		}
	}

}
