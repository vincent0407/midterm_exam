import java.util.Arrays;

public class RangeSearchSystem {

    public static int findFirst(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int firstIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                firstIndex = mid; 
                high = mid - 1;  
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return firstIndex;
    }

    public static int findLast(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int lastIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                lastIndex = mid;
                low = mid + 1;   
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return lastIndex;
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 20, 20, 30, 40, 40, 50, 60, 60, 60, 60};

        int[] testTargets = {20, 60, 10, 35, 100};

        System.out.println("來源陣列: " + Arrays.toString(numbers));
        System.out.println("=================================================");

        for (int target : testTargets) {
            int[] range = searchRange(numbers, target);
            System.out.printf("搜尋目標: %-3d | 索引範圍: [%2d, %2d]", target, range[0], range[1]);
            
            if (range[0] != -1) {
                int count = range[1] - range[0] + 1;
                System.out.printf(" | 出現次數: %d 次\n", count);
            } else {
                System.out.println(" | 出現次數: 0 次 (找不到該目標)");
            }
        }
    }
}
