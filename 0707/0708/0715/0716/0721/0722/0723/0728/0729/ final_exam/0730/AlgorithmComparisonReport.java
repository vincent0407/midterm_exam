import java.util.Arrays;
import java.util.Random;

public class AlgorithmComparisonReport {

    private static long selectionComparisons = 0;
    private static long insertionComparisons = 0;
    private static long mergeComparisons = 0;

    // Selection Sort
    public static void selectionSort(int[] arr) {
        selectionComparisons = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                selectionComparisons++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int[] arr) {
        insertionComparisons = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                insertionComparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] arr) {
        mergeComparisons = 0;
        if (arr.length > 0) {
            mergeSortHelper(arr, 0, arr.length - 1);
        }
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortHelper(arr, left, mid);
        mergeSortHelper(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            mergeComparisons++;
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        String[] dataTypes = {"已排序", "反向排序", "固定亂數"};

        System.out.printf("%-8s | %-10s | %-15s | %-15s | %-15s\n", "資料筆數", "資料狀態", "Selection (比較)", "Insertion (比較)", "Merge Sort (比較)");
        System.out.println("----------------------------------------------------------------------------------");

        for (int size : sizes) {
            for (String type : dataTypes) {
                int[] baseData = generateData(size, type);

                int[] d1 = Arrays.copyOf(baseData, baseData.length);
                int[] d2 = Arrays.copyOf(baseData, baseData.length);
                int[] d3 = Arrays.copyOf(baseData, baseData.length);

                selectionSort(d1);
                insertionSort(d2);
                mergeSort(d3);

                System.out.printf("%-10d | %-10s | %-17d | %-17d | %-17d\n", size, type, selectionComparisons, insertionComparisons, mergeComparisons);
            }
        }

        System.out.println("\n【觀察結論】");
        System.out.println("1. Selection Sort 比較次數只與 N 有關，固定為 N*(N-1)/2，對初始狀態不敏感。");
        System.out.println("2. Insertion Sort 在『已排序』狀態下僅需 N-1 次比較 (O(N))，但在『反向』時需要最高 N*(N-1)/2 次比較。");
        System.out.println("3. Merge Sort 的比較次數穩定落在 O(N log N) 範圍內，在大量資料 (如 1024 筆) 下顯著優於 Selection 與 Insertion Sort。");
        System.out.println("4. 評估演算法效率應以『比較次數/時間複雜度分析』為主，單次執行的毫秒數易受 OS 調度影響，不適宜作為唯一判斷依據。");
    }

    private static int[] generateData(int size, String type) {
        int[] arr = new int[size];
        if (type.equals("已排序")) {
            for (int i = 0; i < size; i++) arr[i] = i;
        } else if (type.equals("反向排序")) {
            for (int i = 0; i < size; i++) arr[i] = size - i;
        } else {
            Random rand = new Random(42); 
            for (int i = 0; i < size; i++) arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
}
