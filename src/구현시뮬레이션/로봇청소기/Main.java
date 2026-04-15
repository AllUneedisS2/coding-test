package 구현시뮬레이션.로봇청소기;

import java.util.*;
import java.io.*;

public class Main {

    static int cleanCount = 0;

    static class Robot {

        // 위치
        int r, c;
        // 방향
        // 0인 경우 북쪽
        // 1인 경우 동쪽
        // 2인 경우 남쪽
        // 3인 경우 서쪽
        int d;
        // 그리드
        int[][] grid;

        // 생성자
        public Robot(int r, int c, int d, int[][] grid) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.grid = grid;
        }

        public int getR() {return this.r;}
        public int getC() {return this.c;}
        public int getD() {return this.d;}
        
        // 반시계방향으로 90도 방향 조정
        public void adjD() {
            d = d - 1;
            if (d < 0) d = 3;
        }
        
        // 현재 칸 청소
        public void cleanThis() {
            // 2는 청소완료
            if(grid[r][c] == 0) {
                grid[r][c] = 2;
                cleanCount++;
            }
        
        }
        
        // 주변 칸 확인 후 청소할 칸이 없으면 true 리턴
        public boolean checkNearNotCleaned() {
            if (
                grid[r-1][c] != 0
                && grid[r][c-1] != 0
                && grid[r+1][c] != 0
                && grid[r][c+1] != 0
            ) return true;
            return false;
        }

        // 한 칸 후진 가능한지
        // 가능하면 true
        public boolean canReverse() {
            switch (d) {
                // 북
                case 0 :
                    if (this.grid[r+1][c] == 1) return false;
                    break;
                // 동
                case 1 :
                    if (this.grid[r][c-1] == 1) return false;
                    break;
                // 남
                case 2 :
                    if (this.grid[r-1][c] == 1) return false;
                    break;
                // 서
                case 3 :
                    if (this.grid[r][c+1] == 1) return false;
                    break;
            }
            return true;
        }

        // 한 칸 후진
        public void reverse() {
            switch (d) {
                // 북
                case 0 :
                    r = r+1;
                    break;
                // 동
                case 1 :
                    c = c-1;
                    break;
                // 남
                case 2 :
                    r = r-1;
                    break;
                // 서
                case 3 :
                    c = c+1;
                    break;
            }
        }

        // 앞쪽 칸 청소됐는지 확인
        // 청소 안되어있다면 true
        public boolean checkFrontNotCleaned() {
            switch (d) {
                // 북
                case 0 :
                    if (grid[r-1][c] == 0) return true;
                    break;
                // 동
                case 1 :
                    if (grid[r][c+1] == 0) return true;
                    break;
                // 남
                case 2 :
                    if (grid[r+1][c] == 0) return true;
                    break;
                // 서
                case 3 :
                    if (grid[r][c-1] == 0) return true;
                    break;
            }
            return false;
        }

        // 앞으로 한 칸 전진
        public void forward() {
            switch (d) {
                // 북
                case 0 :
                    r = r-1;
                    break;
                // 동
                case 1 :
                    c = c+1;
                    break;
                // 남
                case 2 :
                    r = r+1;
                    break;
                // 서
                case 3 :
                    c = c-1;
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken());

        int[][] grid = new int[n][m];
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < m ; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean stop = false;
        Robot robot = new Robot(startR, startC, startD, grid);

        while (!stop) {
            
            // 현재 칸 청소
            robot.cleanThis();

            // 주변에 청소할 칸이 없다면
            if (robot.checkNearNotCleaned()) {
                // 한 칸 후진 할 수 있다면
                if (robot.canReverse()) {
                    // 한 칸 후진
                    robot.reverse();
                }
                // 한 칸 후진 할 수 없다면 (벽이라면)
                else {
                    // 종료
                    stop = true;
                }
            }
            // 주변에 청소되지 않은 칸이 하나라도 있다면
            else {
                for (int i = 0 ; i < 4 ; i++) {
                    // 반시계방향으로 회전
                    robot.adjD();
                    // 앞쪽 방향의 칸이 청소되지 않았다면 전진
                    if (robot.checkFrontNotCleaned()) {
                        robot.forward();
                        break;
                    }
                }
            }
            
        }

        System.out.println(cleanCount);

    }

}
