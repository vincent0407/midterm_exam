import java.util.Scanner;

public class ProductArraySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int totalSpent = 0;         
        int totalPurchasedCount = 0;  
        int totalReplenished = 0;    

        boolean running = true;
        while (running) {
            System.out.println("\n========= 商品管理系統 =========");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依商品編號查詢");
            System.out.println("3. 購買商品並扣除庫存");
            System.out.println("4. 補充商品庫存");
            System.out.println("5. 顯示低庫存商品 (庫存 < 10)");
            System.out.println("6. 顯示全部庫存總價值");
            System.out.println("7. 結束並顯示操作摘要");
            System.out.print("請輸入選項 (1-7): ");

            if (!sc.hasNextInt()) {
                System.out.println("⚠️ 輸入錯誤！請輸入 1 到 7 的整數。");
                sc.next(); 
                continue;
            }

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    queryProductById(sc, names, prices, stocks);
                    break;
                case 3:
                    int purchaseResult = purchaseProduct(sc, names, prices, stocks);
                    if (purchaseResult > 0) {
                        totalSpent += purchaseResult;
                        totalPurchasedCount++;
                    }
                    break;
                case 4:
                    int replenishResult = replenishProduct(sc, names, stocks);
                    if (replenishResult > 0) {
                        totalReplenished += replenishResult;
                    }
                    break;
                case 5:
                    displayLowStockProducts(names, prices, stocks);
                    break;
                case 6:
                    int totalValue = calculateTotalValue(prices, stocks);
                    System.out.println("\n💰 目前全店庫存總價值為: NT$ " + totalValue + " 元");
                    break;
                case 7:
                    displayOperationSummary(totalSpent, totalPurchasedCount, totalReplenished);
                    running = false;
                    break;
                default:
                    System.out.println("⚠️ 無此選項，請重新輸入 1~7。");
            }
        }
        sc.close();
    }

    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n----------------- 商品清單 -----------------");
        System.out.printf("%-6s %-15s %-10s %-10s\n", "編號", "商品名稱", "價格", "庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("[%2d]   %-15s NT$ %-8d %-8d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println("------------------------------------------");
    }

    public static void queryProductById(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入要查詢的商品編號 (1-" + names.length + "): ");
        int id = readValidId(sc, names.length);
        int index = id - 1; 

        System.out.println("\n🔎 查詢結果：");
        System.out.println("商品名稱: " + names[index]);
        System.out.println("商品價格: NT$ " + prices[index] + " 元");
        System.out.println("目前庫存: " + stocks[index] + " 個");
    }

    public static int purchaseProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入欲購買的商品編號 (1-" + names.length + "): ");
        int id = readValidId(sc, names.length);
        int index = id - 1;

        if (stocks[index] == 0) {
            System.out.println("❌ 購買失敗：該商品目前已無庫存！");
            return 0;
        }

        int quantity;
        while (true) {
            System.out.print("目前庫存為 " + stocks[index] + " 個。請輸入購買數量: ");
            if (sc.hasNextInt()) {
                quantity = sc.nextInt();
                if (quantity > 0 && quantity <= stocks[index]) {
                    break;
                }
                System.out.println("⚠️ 數量無效！必須大於 0 且不能超過現有庫存。");
            } else {
                sc.next();
                System.out.println("⚠️ 請輸入正確的數字。");
            }
        }

        int cost = prices[index] * quantity;
        stocks[index] -= quantity; 
        System.out.println("🎉 購買成功！您購買了 " + quantity + " 個 " + names[index] + "，共計 NT$ " + cost + " 元。");
        return cost;
    }

    public static int replenishProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入欲補貨的商品編號 (1-" + names.length + "): ");
        int id = readValidId(sc, names.length);
        int index = id - 1;

        int quantity;
        while (true) {
            System.out.print("目前庫存為 " + stocks[index] + " 個。請輸入欲補貨的數量: ");
            if (sc.hasNextInt()) {
                quantity = sc.nextInt();
                if (quantity > 0) {
                    break;
                }
                System.out.println("⚠️ 補貨數量必須大於 0！");
            } else {
                sc.next();
                System.out.println("⚠️ 請輸入正確的數字。");
            }
        }

        stocks[index] += quantity; 
        System.out.println("📦 補貨成功！" + names[index] + " 的庫存已更新為: " + stocks[index] + " 個。");
        return quantity;
    }

    public static void displayLowStockProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n⚠️ --- 低庫存商品警告 (庫存 < 10) ---");
        boolean found = false;
        System.out.printf("%-6s %-15s %-10s\n", "編號", "商品名稱", "目前庫存");
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("[%2d]   %-15s %-10d\n", (i + 1), names[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("（目前所有商品庫存均安全，無低庫存商品）");
        }
        System.out.println("------------------------------------");
    }

    public static int calculateTotalValue(int[] prices, int[] stocks) {
        int totalValue = 0;
        for (int i = 0; i < prices.length; i++) {
            totalValue += prices[i] * stocks[i];
        }
        return totalValue;
    }

    public static void displayOperationSummary(int totalSpent, int totalPurchasedCount, int totalReplenished) {
        System.out.println("\n=================================");
        System.out.println("🚪 正在結束系統...");
        System.out.println("📊 本次運行操作摘要統計：");
        System.out.println("  - 總購買次數: " + totalPurchasedCount + " 次");
        System.out.println("  - 累計消費總金額: NT$ " + totalSpent + " 元");
        System.out.println("  - 累計補充庫存總量: " + totalReplenished + " 個");
        System.out.println("感謝您使用本商品管理系統，再見！");
        System.out.println("=================================");
    }

    public static int readValidId(Scanner sc, int maxId) {
        int id;
        while (true) {
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (id >= 1 && id <= maxId) {
                    break;
                }
            } else {
                sc.next(); 
            }
            System.out.print("⚠️ 編號錯誤！請重新輸入合法的商品編號 (1-" + maxId + "): ");
        }
        return id;
    }
}
