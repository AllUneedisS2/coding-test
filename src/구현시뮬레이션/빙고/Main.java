package 구현시뮬레이션.빙고;

import java.util.*;
import java.io.*;

public class Main {

    static boolean[][] marked = new boolean[5][5];
    static int result = 0;

    static boolean checkBingo() {

        int count = 0;

        // 수직/수평 체크
        for (int i = 0 ; i < 5 ; i++) {
            boolean row = true, column = true;
            for (int j = 0 ; j < 5 ; j++) {
                // row 중 하나라도 false가 있으면 실패
                if (!marked[i][j]) row = false;
                // column 중 하나라도 false가 있으면 실패
                if (!marked[j][i]) column = false;
            }
            if (row) count++;
            if (column) count++;
        }

        // 대각선 체크1
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int i = 0 ; i < 5 ; i++) {
            // 하나라도 false가 있다면 실패
            if (!marked[i][i]) diagonal1 = false;
            // 하나라도 false가 있다면 실패
            if (!marked[i][4-i]) diagonal2 = false;
        }
        if (diagonal1) count++;
        if (diagonal2) count++;

        if (count >= 3) return true;

        return false;
    }
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 보드 만들기
        int[][] board = new int[5][5];
        // 각 숫자를 인덱스로 하여 값은 위치를 담은 25크기의 배열
        // position[i][0] = 행
        // position[i][1] = 열
        int[][] position = new int[26][2];

        // 값 채워넣기
        for (int r = 0 ; r < 5 ; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0 ; c < 5 ; c++) {
                int token = Integer.parseInt(st.nextToken());
                board[r][c] = token;
                position[token][0] = r;
                position[token][1] = c;
            }
        }
        
        // 사회자가 숫자부르고
        // 해당 숫자 위치의 Marked 마킹하기
        // 빙고인지 확인
        for (int r = 0 ; r < 5 ; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0 ; c < 5 ; c++) {
                int token = Integer.parseInt(st.nextToken());
                int markedR = position[token][0];
                int markedC = position[token][1];

                marked[markedR][markedC] = true;

                result++;
                if (checkBingo()) {
                    System.out.println(result);
                    return;
                }
            }
        }

    }
    
}
