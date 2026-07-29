import java.util.Arrays;
import java.util.Random;

public class SortingExperiment {

    static class Metrics {
        long comparisons = 0;
        long swaps = 0;
        long moves = 0;

        void reset() {
            comparisons = 0;
            swaps = 0;
            moves = 0;
        }

        @Override
        public String toString() {
            return String.format("比較次數: %-4d | 交換/移動次數: %-4d", comparisons, (swaps + moves));
        }
    }

    public static void selectionSort(int[] arr, Metrics m) {
        m.reset();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                m.swaps++;
            }
        }
    }

    public static void insertionSort(int[] arr, Metrics m) {
        m.reset();
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                m.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    m.moves++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int size = 50;
        int[] sortedData = new int[size];
        int[] reversedData = new int[size];
        int[] randomData = new int[size];

        Random rand = new Random(42);
        for (int i = 0; i < size; i++) {
            sortedData[i] = i * 2;
            reversedData[i] = (size - i) * 2;
            randomData[i] = rand.nextInt(100);
        }

        String[] names = {"已排序資料", "反向排序資料", "隨機排列資料"};
        int[][] datasets = {sortedData, reversedData, randomData};
        Metrics metrics = new Metrics();

        System.out.println("=== 排序效能操作統計數據 (資料量 N = 50) ===");
        for (int k = 0; k < 3; k++) {
            System.out.println("\n【測試資料組】： " + names[k]);

            int[] arr1 = Arrays.copyOf(datasets[k], size);
            selectionSort(arr1, metrics);
            System.out.println(" Selection Sort -> " + metrics);

            int[] arr2 = Arrays.copyOf(datasets[k], size);
            insertionSort(arr2, metrics);
            System.out.println(" Insertion Sort -> " + metrics);
        }

        System.out.println("\n================ 觀察結論 ================");
        System.out.println("1. 已排序資料：Insertion Sort 表現最佳 (只需 N-1 次比較，0 次移動)；Selection Sort 比較次數固定為 N*(N-1)/2。");
        System.out.println("2. 反向排序資料：Insertion Sort 比較與移動次數達到最壞情況 O(N^2)；Selection Sort 交換次數極少，但比較次數固定。");
        System.out.println("3. 隨機資料：Insertion Sort 在近乎排序的狀況下平均效能優於 Selection Sort。");
    }
}
