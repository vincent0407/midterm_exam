import java.util.Scanner;

public class ProductIdSearchPractice {

    public static void main(String[] args) {
        int[] productIds = {105, 302, 88, 450, 201, 789, 12, 630};

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的商品編號: ");
        int target = scanner.nextInt();

        int index = -1;
        int compareCount = 0; 

        // 循序搜尋
        for (int i = 0; i < productIds.length; i++) {
            compareCount++;
            if (productIds[i] == target) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            System.out.println("找到商品！索引位置為: " + index);
        } else {
            System.out.println("找不到該商品編號！");
        }
        System.out.println("實際比較次數: " + compareCount);

        scanner.close();
    }
}
