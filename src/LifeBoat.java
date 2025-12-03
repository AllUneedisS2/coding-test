import java.util.*;

public class LifeBoat {

    public static void main(String[] args) {
        LifeBoat lifeBoat = new LifeBoat();
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        int result = lifeBoat.solution(people, limit);
        System.out.println(result);
        
    }

    private int solution(int[] people, int limit) {

        // 1. 사람들을 몸무게 기준으로 정렬
        Arrays.sort(people, Comparator.reverseOrder());
        

        return 0;
    }
    

}
