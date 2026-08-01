import java.util.Arrays;

public class MergeArrayPractice {

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int i = 0; 
        int j = 0; 
        int k = 0; 

        int[] result = new int[arr1.length + arr2.length];

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1：長度不同、包含重複值與負數 ===");
        int[] arr1 = {-5, -2, 3, 8, 8, 12};
        int[] arr2 = {-3, 0, 8, 15, 20};
        System.out.println("陣列 1: " + Arrays.toString(arr1));
        System.out.println("陣列 2: " + Arrays.toString(arr2));
        System.out.println("合併結果: " + Arrays.toString(mergeSortedArrays(arr1, arr2)));

        System.out.println("\n=== 測試 2：其中一個陣列為空 ===");
        int[] emptyArr = {};
        int[] arr3 = {1, 4, 9};
        System.out.println("空陣列 + 陣列 3: " + Arrays.toString(mergeSortedArrays(emptyArr, arr3)));
    }
}
