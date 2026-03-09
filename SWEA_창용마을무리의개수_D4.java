package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_창용마을무리의개수_D4 {
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
			
			for(int i=1;i<=N;i++) {
				parents[i]=i;
			}
			for(int m=0;m<M;m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
			}
			
			Set<Integer> set = new HashSet<>();
			for(int i=1;i<=N;i++) {
				int rootNum = getRoot(i);
				set.add(rootNum);
			}
			sb.append("#"+t).append(" ");
			sb.append(set.size()+"\n");
			
		}
		System.out.println(sb.toString());
	}
	static int getRoot(int num) {
		if(num==parents[num]) {
			return num;
		}
		return parents[num] = getRoot(parents[num]);
	}
	
	static void union(int a,int b) {
		int aRoot = getRoot(a);
		int bRoot = getRoot(b);
		if(aRoot != bRoot) {//이미 같으면 건너뜀
			parents[bRoot] = aRoot;
		}
	}

}
