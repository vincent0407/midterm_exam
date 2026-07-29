import java.util.Arrays;

public class SortingDebugReport {

    public static void bug1_OutOfBounds(int[] arr) {
        System.out.println("\n--- 測試 錯誤版本 1 (內層範圍錯誤) ---");
        try {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j <= arr.length; j++) { // 錯誤點：<= arr.length
                    if (arr[j] < arr[i]) {
                        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("捕獲預期錯誤: " + e);
        }
    }
    public static void bug2_KeyNotSaved(int[] arr) {
        System.out.println("\n--- 測試 錯誤版本 2 (key 未保存) ---");
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = arr[i]; 
        }
        System.out.println("錯誤執行結果 (資料損壞): " + Arrays.toString(arr));
    }

    public static void bug3_WrongDirection(int[] arr) {
        System.out.println("\n--- 測試 錯誤版本 3 (比較方向錯誤) ---");
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < key) { 
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        System.out.println("錯誤執行結果 (預期升冪卻變降冪): " + Arrays.toString(arr));
    }

    public static void fixedVersion(int[] arr) {
        System.out.println("\n--- 測試 正確修正版本 ---");
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; 
            int j = i - 1;
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        System.out.println("正確修正結果 (升冪): " + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] testData = {5, 2, 9, 1, 3};

        System.out.println("原始測試資料: " + Arrays.toString(testData));

        bug1_OutOfBounds(Arrays.copyOf(testData, testData.length));
        bug2_KeyNotSaved(Arrays.copyOf(testData, testData.length));
        bug3_WrongDirection(Arrays.copyOf(testData, testData.length));
        fixedVersion(Arrays.copyOf(testData, testData.length));
    }
}
