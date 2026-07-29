import java.util.Arrays;

public class ProductSortingSystem {

    public static void customSort(StoreProduct[] arr, String mode) {
        for (int i = 1; i < arr.length; i++) {
            StoreProduct key = arr[i];
            int j = i - 1;

            while (j >= 0 && needMove(arr[j], key, mode)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static boolean needMove(StoreProduct current, StoreProduct key, String mode) {
        switch (mode) {
            case "PRICE_ASC": 
                return current.getPrice() > key.getPrice();
            case "PRICE_DESC": 
                return current.getPrice() < key.getPrice();
            case "STOCK_DESC": 
                return current.getStock() < key.getStock();
            default:
                return false;
        }
    }

    public static StoreProduct[] cloneArray(StoreProduct[] src) {
        return Arrays.copyOf(src, src.length);
    }

    public static void printList(String title, StoreProduct[] list) {
        System.out.println("\n--- " + title + " ---");
        for (StoreProduct p : list) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        StoreProduct[] original = {
            new StoreProduct("P01", "筆記型電腦", 35000, 10),
            new StoreProduct("P02", "智慧手機", 28000, 25),
            new StoreProduct("P03", "藍芽耳機", 3000, 50),
            new StoreProduct("P04", "機械鍵盤", 2500, 30),
            new StoreProduct("P05", "電競滑鼠", 1500, 45),
            new StoreProduct("P06", "螢幕", 8000, 15),
            new StoreProduct("P07", "顯卡", 22000, 5),
            new StoreProduct("P08", "記憶體", 1800, 60),
            new StoreProduct("P09", "固態硬碟", 2400, 40),
            new StoreProduct("P10", "電源供應器", 3200, 20)
        };

        StoreProduct[] priceAsc = cloneArray(original);
        customSort(priceAsc, "PRICE_ASC");
        printList("排序欄位: 價格 | 方向: 升冪 (小到大)", priceAsc);

        StoreProduct[] priceDesc = cloneArray(original);
        customSort(priceDesc, "PRICE_DESC");
        printList("排序欄位: 價格 | 方向: 降冪 (大到小)", priceDesc);

        StoreProduct[] stockDesc = cloneArray(original);
        customSort(stockDesc, "STOCK_DESC");
        printList("排序欄位: 庫存 | 方向: 降冪 (大到小)", stockDesc);
    }
}
