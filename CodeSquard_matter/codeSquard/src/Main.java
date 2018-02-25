import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Random;

public class Main {

    public int checkStrike(String[] data, String[] inputData) {
        int strike = 0;
        for(int i=0; i<3; i++) {
            if (data[i].equals(inputData[i])) {
                inputData[i] = "0";
                strike++;
            } // if
        } // for
        return strike;
    }

    public int checkBall(LinkedHashSet<String> linkset, String[] inputData) {
        int ball = 0;
        for (int i=0; i<3; i++) {
            if (inputData[i].equals("0")) continue;
            if (linkset.contains(inputData[i])) ball++;
        }
        return ball;
    }

    public LinkedHashSet<String> createNumber(LinkedHashSet<String> linkset) {
        Random rand = new Random();
        while(true) {
            if (linkset.size() == 3) break;
            int a = rand.nextInt(9)+1;
            linkset.add(String.valueOf(a));
        } // while

        return linkset;
    }

    public static void main (String[] args) throws Exception {
        Main main = new Main();
        LinkedHashSet<String> linkset = new LinkedHashSet<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputData;
        int strike = 0, ball = 0;

        // 컴퓨터가 3개의 숫자를 중복없이 만드는 부분
        // 생성 후 삽입된 숫자의 순서를 보존하기위해 LinkedHashSet 사용
        linkset = main.createNumber(linkset);

        // Set >> Array
        String[] data = linkset.toArray(new String[linkset.size()]);

        while(true) {
            System.out.print("숫자를 입력해주세요 ex)123 : ");
            inputData = bf.readLine().split("");

            // 스트라이크 체크 부분
            strike = main.checkStrike(data, inputData);
            if (strike == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                return;
            } // if

            // 볼 체크 부분
            ball = main.checkBall(linkset, inputData);

            // 낫팅 체크 부분
            if (strike == 0 && ball == 0) {
                System.out.println("NOTHING");
                continue;
            }

            System.out.println(strike + "스트라이크 " + ball + "볼");

            strike = 0; ball = 0;
        } // while
    }
}