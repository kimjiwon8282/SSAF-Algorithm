package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class น้มุG5_ABCDE_13023 {
	static int N;
	static int M;
	static List<Integer>[] list;	
	static boolean isExist;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i=0;i<N;i++){
			list[i] = new ArrayList<Integer>();
		}
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			list[n1].add(n2);
			list[n2].add(n1);
		}
		
		for(int start = 0;start<N;start++) {
			visited[start] = true;
			dfs(1,start);
			visited[start] = false;
		}
		if(isExist) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
	}
	static void dfs(int idx,int node) {
		if(idx==5) {
			isExist = true;
			return;
		}
		for(int num : list[node]) {
			if(visited[num]) continue;
			visited[num] = true;
			dfs(idx+1,num);
			visited[num] = false;
		}
	}
	
	

}
