package 구현시뮬레이션.숫자정사각형;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 배열 선언
        char[][] grid = new char[n][m];

        // grid 생성
        for (int i = 0 ; i < n ; i++) {
            String line = br.readLine();
            for(int j = 0 ; j < m ; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        // 최대 정사각형의 한 변의 길이
        int maxSize = Math.min(n, m);

        // 가장큰 정사각형부터 2까지 탐색 (1은 탐색할 필요 X)
        for (int s = maxSize ; s >= 2 ; s--) {
            // r = 정사각형의 좌상단 행 위치
            // r + s - 1 값이 n 미만이어야함
            for (int r = 0 ; r + s - 1 < n ; r++) {
                // c = 정사각형의 좌상단 열 위치
                // c + s - 1 값이 m 미만이어야함
                for (int c = 0 ; c + s - 1 < m ; c++) {
                    // 좌상단
                    char A = grid[r][c];
                    // 우상단
                    char B = grid[r][c+s-1];
                    // 좌하단
                    char C = grid[r+s-1][c];
                    // 우하단
                    char D = grid[r+s-1][c+s-1];
                    
                    if (A == B && B == C && C == D) {
                        System.out.println(s*s);
                        return;
                    }
                }
            }
        }

        // 모두 탐색했지만 없다면 1
        System.out.println(1);

    }
}
