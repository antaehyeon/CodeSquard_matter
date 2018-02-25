import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Random;

public class Main {
    public static void main (String[] args) throws Exception {
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers;
        int strike = 0;
        int ball = 0;
        Random r = new Random();

        // 컴퓨터가 3개의 숫자를 중복없이 만드는 부분
        // 생성 후 삽입된 숫자의 순서를 보존하기위해 LinkedHashSet 사용
        while(true) {
            if (lhs.size() == 3) break;
            int a = r.nextInt(9)+1;
            lhs.add(String.valueOf(a));
        } // while

        System.out.println(lhs);

        while(true) {
            System.out.print("숫자를 입력해주세요 ex)123 : ");
            numbers = bf.readLine().split("");

            String[] data = lhs.toArray(new String[lhs.size()]);

            for(int i=0; i<3; i++) {
                if (data[i].equals(numbers[i])) {
                    numbers[i] = null;
                    strike++;
                }

                if (strike == 3) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    return;
                }
            } // for

            for (int i=0; i<3; i++) {
                if (numbers[i].equals(null)) continue;
                if (lhs.contains(numbers[i])) ball++;
            }

            if (strike == 0 && ball == 0) {
                System.out.println("NOTHING");
                continue;
            }

            System.out.println(strike + "스트라이크 " + ball + "볼");

            strike = 0;
            ball = 0;
        } // while
    }
}