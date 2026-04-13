package 구현시뮬레이션.색종이;

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {

        // 100x100 도화지
        // true 색칠
        // false 빈 공간
        boolean[][] canvas = new boolean[100][100];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정사각형 갯수
        int n = Integer.parseInt(br.readLine().trim());

        // 모든 정사각형으로 색칠
        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            for (int j = x ; j < x+10 ; j++) {
                for (int k = y ; k < y+10 ; k++) {
                    canvas[j][k] = true;
                }
            }
        }

        // 카운트
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(canvas[i][j]) count++;
            }
        }

        System.out.println(count);

    }

}
