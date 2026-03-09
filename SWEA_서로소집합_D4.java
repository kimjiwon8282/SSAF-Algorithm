package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_서로소집합_D4 {
	static int N,M;
	static int[][] orders;
	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<=T;t++) {
			sb.append("#"+t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			orders = new int[M][3];
			parents = new int[N+1];
			
			for(int i=1;i<=N;i++) {
				parents[i]=i;
			}
			for(int m=0;m<M;m++) {
				st = new StringTokenizer(br.readLine());
				orders[m][0] = Integer.parseInt(st.nextToken());
				orders[m][1] = Integer.parseInt(st.nextToken());
				orders[m][2] = Integer.parseInt(st.nextToken());
			}
			for(int m=0;m<M;m++) {
				int order = orders[m][0];
				int a = orders[m][1];
				int b = orders[m][2];
				if(order==1) { //같은 집합 포함되어 있는지
					if(getRoot(a)==getRoot(b)) {
						sb.append(1);//같은 집합이라면
					}else {
						sb.append(0); //다른 집합에 속했다면
					}
				}else { //union연산
					union(a,b);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static int getRoot(int num) {
		if(parents[num]==num) {
			return num;
		}
		return parents[num] = getRoot(parents[num]);
	}
	
	static void union(int a,int b) {
		int aRoot = getRoot(a);
		int bRoot = getRoot(b);
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}
	}
}
