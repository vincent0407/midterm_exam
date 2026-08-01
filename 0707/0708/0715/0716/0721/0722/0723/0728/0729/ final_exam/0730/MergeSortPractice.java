import java.util.Arrays;

public class MergeSortPractice {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return; // 停止條件
        }

        int mid = left + (right - left) / 2;

        System.out.printf("拆分範圍: [%d..%d] -> [%d..%d] 與 [%d..%d]\n", 
                left, right, left, mid, mid + 1, right);

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);

        System.out.printf("合併 [%d..%d] 結果: %s\n", 
                left, right, Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(String[] args) {
        System.out.println("=== 測試指定資料 {41, 12, 35, 8, 27, 19, 50, 3} ===");
        int[] data = {41, 12, 35, 8, 27, 19, 50, 3};
        mergeSort(data, 0, data.length - 1);
        System.out.println("最終排序結果: " + Arrays.toString(data));

        System.out.println("\n=== 測試邊界情況：空陣列、單筆、已排序、反向 ===");
        runTest("空陣列", new int[]{});
        runTest("單筆資料", new int[]{42});
        runTest("已排序", new int[]{1, 2, 3, 4, 5});
        runTest("反向資料", new int[]{5, 4, 3, 2, 1});
    }

    private static void runTest(String label, int[] arr) {
        System.out.println("\n-- " + label + " --");
        if (arr.length > 0) {
            mergeSort(arr, 0, arr.length - 1);
        }
        System.out.println("結果: " + Arrays.toString(arr));
    }
}
