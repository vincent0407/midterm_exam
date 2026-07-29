public class ScoreRankingPractice {

    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 92, 59, 100, 60, 45, 78};

        for (int i = 0; i < scores.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < scores.length; j++) {
                if (scores[j] > scores[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int temp = scores[i];
                scores[i] = scores[maxIndex];
                scores[maxIndex] = temp;
            }
        }

        System.out.println("=========================================");
        System.out.println("名次\t分數\t是否及格");
        System.out.println("=========================================");

        int rank = 1;
        for (int i = 0; i < scores.length; i++) {
            if (i > 0 && scores[i] != scores[i - 1]) {
                rank = i + 1;
            }
            String status = (scores[i] >= 60) ? "及格" : "不及格";
            System.out.printf("第 %d 名\t%d 分\t%s\n", rank, scores[i], status);
        }
    }
}
