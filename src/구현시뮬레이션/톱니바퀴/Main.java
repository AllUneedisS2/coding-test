package 구현시뮬레이션.톱니바퀴;

import java.util.*;
import java.io.*;

// 남겨진 치킨집 1,2,3,...M으로 
public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 톱니 바퀴 배열 생성 (S극: 1, N극: 0)
        // gear[0][0] = 첫 번째 톱니바퀴의 12시 방향의 극
        int[][] gear = new int[4][8];
        for (int i = 0 ; i < 4 ; i++) {
            st = new StringTokenizer(br.readLine());
            String token = st.nextToken();
            for (int j = 0 ; j < 8 ; j++) {
                gear[i][j] = token.charAt(j) - '0';
            }
        }

        // k번 회전
        int k = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < k ; i++) {

            st = new StringTokenizer(br.readLine());

            // 이번에 처음 돌려야하는 톱니 정보
            int startGear = Integer.parseInt(st.nextToken()) - 1;
            int startGearDirection = Integer.parseInt(st.nextToken()); // -1: 반시계방향, 1: 시계방향
            
            // 돌려야하는 톱니 정보 배열 생성
            int[] gearToRotate = new int[4];

            // 이번에 처음 돌려야하는 톱니 정보 추가
            gearToRotate[startGear] = startGearDirection;

            // 처음 돌려야하는 톱니 정보로부터 돌아가야하는 오른쪽 방향의 톱니 정보 추가
            for (int j = startGear + 1 ; j < 4 ; j++) {
                // 다음 톱니의 6번째 극이 이전 톱니의 2번째 극과 다르다면
                if (gear[j][6] != gear[j-1][2]) {
                    // 다음 톱니는 이전 톱니의 돌려야하는 방향과 반대
                    gearToRotate[j] = -gearToRotate[j-1];
                }
                // 같다면 스탑
                else {
                    break;
                }
            }
            
            // 처음 돌려야하는 톱니 정보로부터 돌아가야하는 왼쪽 방향의 톱니 정보 추가
            for (int j = startGear - 1 ; j >= 0 ; j--) {
                // 다음 톱니의 2번째 극이 이전 톱니의 6번째 극과 다르다면
                if (gear[j][2] != gear[j+1][6]) {
                    // 다음 톱니는 이전 톱니의 돌려야하는 방향과 반대
                    gearToRotate[j] = -gearToRotate[j+1];
                }
                // 같다면 스탑
                else {
                    break;
                }
            }
            
            // 4개의 톱니 중 돌려야하는 톱니 돌리기 (돌아가야하는 톱니 정보 배열로 부터)
            rotate(gear, gearToRotate);
        }

        // 계산
        int result = 0;
        for (int i = 0 ; i < 4 ; i++) {
            int temp = gear[i][0];
            if (temp == 1) result += Math.pow(2, i);
        }
        
        System.out.println(result);

    }

    static void rotate(int[][] gear, int[] gearToRotate) {
        for (int i = 0 ; i < 4 ; i++) {
            // 방향 정보
            int direction = gearToRotate[i];
            if (direction == 0) continue;
            // 시계방향으로 돌리기
            if (direction == 1) {
                int last = gear[i][7];
                for (int j = 7 ; j >= 1 ; j--) {
                    gear[i][j] = gear[i][j-1];
                }
                gear[i][0] = last;
            } 
            // 반시계
            else {
                int first = gear[i][0];
                for (int j = 0 ; j < 6 ; j++) {
                    gear[i][j] = gear[i][j+1];
                }
                gear[i][7] = first;
            }
        }

    }
    
}
