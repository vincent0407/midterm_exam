import java.util.Arrays;

public class SelectionSortPractice {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列為空或只有一個元素，無需排序。陣列內容: " + Arrays.toString(arr));
            return;
        }

        int compareCount = 0; 
        int swapCount = 0;    

        System.out.println("初始陣列: " + Arrays.toString(arr));

        for (int start = 0; start < arr.length - 1; start++) {
            int minIndex = start;
            for (int j = start + 1; j < arr.length; j++) {
                compareCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != start) {
                int temp = arr[start];
                arr[start] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }

            System.out.printf("輪次 start: %d | 選中最小值的索引: %d | 陣列內容: %s\n",
                    start, minIndex, Arrays.toString(arr));
        }

        System.out.println("\n--- 統計結果 ---");
        System.out.println("總比較次數: " + compareCount);
        System.out.println("實際交換次數: " + swapCount);
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1: 標準題目陣列 ===");
        int[] data1 = {42, 18, 35, 7, 29, 14};
        selectionSort(data1);

        System.out.println("\n=== 測試 2: 空陣列 ===");
        int[] data2 = {};
        selectionSort(data2);

        System.out.println("\n=== 測試 3: 單一元素陣列 ===");
        int[] data3 = {99};
        selectionSort(data3);
    }
}
