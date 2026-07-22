import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("=== 動態成績管理系統 ===");
        System.out.println("請輸入成績（範圍：0~100，輸入 -1 結束輸入）：");

        while (true) {
            System.out.print("請輸入成績: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ 錯誤：請輸入有效的整數！");
                scanner.next(); 
                continue;
            }

            int input = scanner.nextInt();

            if (input == -1) {
                break;
            }

            if (input < 0 || input > 100) {
                System.out.println("❌ 錯誤：成績必須介於 0 到 100 之間！");
                continue;
            }

            scores.add(input);
        }

        System.out.println("\n========== 統計結果 ==========");
        int count = scores.size();
        System.out.println("總筆數: " + count + " 筆");

        if (count == 0) {
            System.out.println("尚無有效的成績資料。");
        } else {
            System.out.printf("平均分數: %.2f 分\n", getAverage(scores));
            System.out.println("最高分數: " + getMax(scores) + " 分");
            System.out.println("最低分數: " + getMin(scores) + " 分");
            
            // 取得及格名單 (>= 60)
            ArrayList<Integer> passedScores = getPassedScores(scores);
            System.out.println("及格成績列表 (>=60): " + passedScores);
        }

        scanner.close();
    }

    public static double getAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) return 0.0;
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    public static int getMax(ArrayList<Integer> scores) {
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    public static int getMin(ArrayList<Integer> scores) {
        int min = scores.get(0);
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    public static ArrayList<Integer> getPassedScores(ArrayList<Integer> scores) {
        ArrayList<Integer> passed = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passed.add(score);
            }
        }
        return passed;
    }
}
