import java.util.Arrays;

public class InventorySearchPractice {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] inventory = {105, 101, 112, 103, 108, 102, 110, 104, 107, 109, 106, 111};

        System.out.println("排序前庫存編號: " + Arrays.toString(inventory));

        mergeSort(inventory, 0, inventory.length - 1);
        System.out.println("排序後庫存編號: " + Arrays.toString(inventory));

        int[] targets = {inventory[0], inventory[inventory.length - 1], 999};
        
        System.out.println("\n=== 搜尋測試 ===");
        for (int target : targets) {
            int index = binarySearch(inventory, target);
            if (index != -1) {
                System.out.println("查詢編號 " + target + " -> 找到於索引 " + index);
            } else {
                System.out.println("查詢編號 " + target + " -> 找不到該編號 (索引 -1)");
            }
        }
    }
}
