import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllOccurrenceSearch {

    public static void main(String[] args) {
        int[] data = {15, 42, 8, 42, 99, 23, 42, 8, 10};

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的數值: ");
        int target = scanner.nextInt();

        List<Integer> foundIndices = new ArrayList<>();
        int compareCount = 0; 

        for (int i = 0; i < data.length; i++) {
            compareCount++;
            if (data[i] == target) {
                foundIndices.add(i);
            }
        }

        System.out.println("\n=== 搜尋結果 ===");
        if (!foundIndices.isEmpty()) {
            System.out.println("找到目標！符合的索引位置為: " + foundIndices);
            System.out.println("總共出現次數: " + foundIndices.size() + " 次");
        } else {
            System.out.println("找不到該數值！");
            System.out.println("總共出現次數: 0 次");
        }
        System.out.println("實際比較次數: " + compareCount + " 次");

        scanner.close();
    }
}
