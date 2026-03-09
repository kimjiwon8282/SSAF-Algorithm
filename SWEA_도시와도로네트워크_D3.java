package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//22,272 kb, 77 ms
public class SWEA_도시와도로네트워크_D3 {
	static int N;
	static int M;
	static int[] parents;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			
			int A[] = new int[M];
			int B[] = new int[M];
			
			for(int i=1;i<=N;i++) {
				parents[i] = i;
			}
			
			for(int m=0;m<M;m++) {
				st = new StringTokenizer(br.readLine());
				A[m] = Integer.parseInt(st.nextToken());
				B[m] = Integer.parseInt(st.nextToken());
			}
			boolean cycle = false;
			for(int m=0;m<M;m++) {
				int a = A[m];
				int b = B[m];
				if(getRoot(a)==getRoot(b)) {
					cycle = true;
					break;
				}else {
					union(a,b);
				}
			}
			if(cycle) {
				sb.append("#"+t).append(" ").append("YES\n");
			}else {
				sb.append("#"+t).append(" ").append("NO\n");
			}
		}
		System.out.println(sb.toString());
		
	}
	static int getRoot(int num) {
		if(parents[num]==num) {
			return num;
		}
		return getRoot(parents[num]);
	}
	
	static void union(int a,int b) {
		int aRoot = getRoot(a);
		int bRoot = getRoot(b);
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}
	}
}
