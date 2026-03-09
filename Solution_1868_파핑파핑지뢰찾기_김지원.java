package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_1868_파핑파핑지뢰찾기_김지원 {
    static int N;
    static char[][] graph;
    static int dr[] = {0,0,1,-1,-1,-1,1,1};
    static int dc[] = {1,-1,0,0,-1,1,-1,1};
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            graph = new char[N][N];
            
            for(int r=0; r<N; r++) {
                graph[r] = br.readLine().toCharArray();
            }
            
            int clickCount = 0; // 최소 클릭 횟수
            
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(graph[r][c] == '.' && getCount(r, c) == 0) {
                        clickCount++;
                        bfs(r, c); 
                    }
                }
            }
            
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(graph[r][c] == '.') {
                        clickCount++;
                    }
                }
            }
            
            sb.append("#").append(t).append(" ").append(clickCount).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    static void bfs(int startR, int startC) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startR, startC});
        graph[startR][startC] = 'V'; 
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int cr = curr[0];
            int cc = curr[1];
            
            for(int d=0; d<8; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                
                if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
                
                if(graph[nr][nc] == '.') {
                    graph[nr][nc] = 'V'; 
                    
                    if(getCount(nr, nc) == 0) {
                        q.offer(new int[] {nr, nc});
                    }
                }
            }
        }
    }
    
    static int getCount(int cr, int cc) {
        int count = 0;
        for(int d=0; d<8; d++) {
            int nr = cr + dr[d];
            int nc = cc + dc[d];
            if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
            
            if(graph[nr][nc] == '*') { 
                count++;
            }
        }
        return count;
    }
}