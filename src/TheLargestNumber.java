import java.util.*;
import java.lang.*;

public class TheLargestNumber {

    public static void main(String[] args) {
        TheLargestNumber theLargestNumber = new TheLargestNumber();
        int[] numbers = {6, 10, 2};
        String result = theLargestNumber.solution(numbers);
        System.out.println(result);
    }

    public String solution(int[] numbers) {

        String answer = "";

        // 1. 숫자를 문자열로 변환
        String[] strs = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }

        // 2. 문자열 정렬 → (X+Y) vs (Y+X) 비교
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));

        // 3. 정렬된 숫자들을 합쳐서 결과 반환
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        String result = sb.toString();

        return result.startsWith("0") ? "0" : result;

    }

}
