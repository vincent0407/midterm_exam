public class SearchEfficiencyReport {

    public static int sequentialSearch(int[] arr, int target) {
        int compareCount = 0;
        for (int num : arr) {
            compareCount++;
            if (num == target) {
                break;
            }
        }
        return compareCount;
    }

    public static int binarySearch(int[] arr, int target) {
        int compareCount = 0;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            compareCount++;
            if (arr[mid] == target) {
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return compareCount;
    }

    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};

        System.out.println("===============================================================");
        System.out.println("                     搜尋演算法效率比較報告                     ");
        System.out.println("===============================================================");
        
        for (int size : sizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = (i + 1) * 2; 
            }

            int first = data[0];                  
            int last = data[size - 1];            
            int notExist = -1;                    

            System.out.printf("\n--- 資料量 N = %4d ---\n", size);
            System.out.printf("%-12s | %-15s | %-15s\n", "測試情境", "循序搜尋比較次數", "二分搜尋比較次數");
            System.out.println("---------------------------------------------------------------");

            int seqFirst = sequentialSearch(data, first);
            int binFirst = binarySearch(data, first);
            System.out.printf("%-14s | %-19d | %-15d\n", "第一筆資料", seqFirst, binFirst);

            int seqLast = sequentialSearch(data, last);
            int binLast = binarySearch(data, last);
            System.out.printf("%-14s | %-19d | %-15d\n", "最後一筆資料", seqLast, binLast);

            int seqNotExist = sequentialSearch(data, notExist);
            int binNotExist = binarySearch(data, notExist);
            System.out.printf("%-14s | %-19d | %-15d\n", "不存在的資料", seqNotExist, binNotExist);
        }

        System.out.println("\n===============================================================");
        System.out.println("                         觀察與結論                             ");
        System.out.println("===============================================================");
        System.out.println("1. 循序搜尋 (Sequential Search):");
        System.out.println("   - 時間複雜度為 O(N)。比較次數隨資料量呈線性成長。");
        System.out.println("   - 最好情況（第一筆）僅需 1 次比較；最壞情況（最後一筆或不存在）需要 N 次比較。");
        System.out.println();
        System.out.println("2. 二分搜尋 (Binary Search):");
        System.out.println("   - 時間複雜度為 O(log N)。比較次數隨資料量呈對數級成長。");
        System.out.println("   - 最壞情況下的比較次數約為 log2(N) + 1，即使資料量擴大至 1024 筆，最難情境也只需約 11 次比較。");
        System.out.println();
        System.out.println("3. 綜合比較:");
        System.out.println("   - 當資料量較小（如 N=16）且搜尋第一筆時，循序搜尋表現較優；但資料量擴大時，二分搜尋效率優勢極為顯著。");
    }
}
