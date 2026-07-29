import java.util.Arrays;

public class InsertionSortPractice {

    public static void insertionSort(int[] arr) {
        int compareCount = 0; 
        int shiftCount = 0;   

        System.out.println("初始陣列: " + Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                compareCount++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    shiftCount++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key; 

            System.out.printf("Key: %-2d | 插入位置: %d | 陣列內容: %s\n", key, (j + 1), Arrays.toString(arr));
        }

        System.out.println("\n--- 統計結果 ---");
        System.out.println("比較次數: " + compareCount);
        System.out.println("元素右移次數: " + shiftCount);
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1: 題目指定陣列 ===");
        insertionSort(new int[]{30, 10, 20, 50, 40, 5});

        System.out.println("\n=== 測試 2: 已排序資料 ===");
        insertionSort(new int[]{5, 10, 20, 30, 40, 50});

        System.out.println("\n=== 測試 3: 反向排序資料 ===");
        insertionSort(new int[]{50, 40, 30, 20, 10, 5});

        System.out.println("\n=========================================");
        System.out.println("【觀察結論】");
        System.out.println("「反向排序資料」的移動次數最多。");
        System.out.println("原因：在完全逆序的情況下，每個新插入的元素都需要與左側所有元素比較，並將它們全部向右移動一格。");
        System.out.println("=========================================");
    }
}
