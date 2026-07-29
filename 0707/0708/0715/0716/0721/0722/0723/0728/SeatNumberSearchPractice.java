import java.util.Scanner;

public class SeatNumberSearchPractice {

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            System.out.printf("low: %d, mid: %d, high: %d (目前 mid 值: %d)\n", low, mid, high, arr[mid]);

            if (arr[mid] == target) {
                return mid; 
            } else if (arr[mid] < target) {
                low = mid + 1; 
            } else {
                high = mid - 1;
            }
        }
        return -1; 
    }

    public static void main(String[] args) {
        int[] seatNumbers = {101, 105, 110, 115, 120, 125, 130, 135, 140, 145, 150, 155};

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的座位編號: ");
        int target = scanner.nextInt();

        System.out.println("--- 開始搜尋 ---");
        int result = binarySearch(seatNumbers, target);

        if (result != -1) {
            System.out.println("搜尋結果：座位在索引 " + result);
        } else {
            System.out.println("搜尋結果：找不到該座位編號！");
        }

        scanner.close();
    }
}
