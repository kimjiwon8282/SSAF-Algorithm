package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_15686_치킨배달_G5 {
	static int N;
	static int M;
	static int[][] graph;
	static int best;
	static class House{
		int r;
		int c;
		int distance = Integer.MAX_VALUE;
		
		public House(int r,int c) {
			super();
			this.r=r;
			this.c=c;
		}
	}
	static class Chicken{
		int r;
		int c;
		
		public Chicken(int r,int c) {
			super();
			this.r=r;
			this.c=c;
		}
	}
	
	static List<House> houses;
	static List<Chicken> chickens;
	static int[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		selected = new int[M];
		
		graph = new int[N][N];
		
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
				if(graph[r][c]==2) { //치킨집
					chickens.add(new Chicken(r,c));
				}
				if(graph[r][c]==1) { //집
					houses.add(new House(r,c));
				}
			}
		}
		best = Integer.MAX_VALUE;
		combination(0,0);
		System.out.println(best);
	}
	
	static void combination(int idx,int start) {
		if(idx==M) {
			//선택된 치킨집만 가지고 치킨거리 구하기
			for(int h=0;h<houses.size();h++) {
				House house = houses.get(h);
				int cr = house.r;
				int cc = house.c;
				for(int c=0;c<M;c++) {
					Chicken chicken = chickens.get(selected[c]);
					int dr = Math.abs(cr-chicken.r);
					int dc = Math.abs(cc-chicken.c);
					int temp = dr+dc;
					house.distance = Math.min(house.distance, temp);
				}
			}
			
			int sum=0;
			//모든 집의 치킨거리 합 구하기
			for(House house : houses) {
				sum+=house.distance;
			}
			best = Math.min(best,sum);
			for(House house : houses) {
				house.distance = Integer.MAX_VALUE;
			}
			return;
		}
		for(int i=start;i<chickens.size();i++) {
			selected[idx] = i;
			combination(idx+1,i+1);
		}
	}

}