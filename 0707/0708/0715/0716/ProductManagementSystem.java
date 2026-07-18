import java.util.Scanner;

public class ProductManagementSystem {
    private static Product[] products = new Product[10];
    private static int productCount = 0; 
    private static Scanner scanner = new Scanner(System.in);

    private static int totalSales = 0;
    private static int totalRestocks = 0;
    private static int priceUpdates = 0;
    private static int addedProducts = 0;

    public static void main(String[] args) {
        initProducts();

        while (true) {
            printMenu();
            int choice = getIntInput("請選擇功能 (1-9): ");
            
            switch (choice) {
                case 1: showAllProducts(); break;
                case 2: searchProduct(); break;
                case 3: addProductMenu(); break;
                case 4: sellProductMenu(); break;
                case 5: restockProductMenu(); break;
                case 6: updatePriceMenu(); break;
                case 7: showLowStockProducts(); break;
                case 8: showTotalValue(); break;
                case 9: 
                    showSummary(); 
                    return;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
            System.out.println("\n====================================");
        }
    }

    private static void initProducts() {
        products[0] = new Product("Apple", 30, 20);
        products[1] = new Product("Banana", 15, 5); 
        products[2] = new Product("Orange", 25, 50);
        products[3] = new Product("Milk", 85, 3);   
        products[4] = new Product("Bread", 40, 15);
        productCount = 5;
        System.out.println("系統初始化成功，已匯入 5 項預設商品。");
    }

    private static void printMenu() {
        System.out.println("\n--- 商品管理系統選單 ---");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("錯誤：請輸入有效的整數數字！");
            }
        }
    }

    private static int findProductIndex(String name) {
        if (name == null) return -1;
        String cleanName = name.trim();
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(cleanName)) {
                return i;
            }
        }
        return -1;
    }

    private static void addProduct(String name, int price, int stock) {
        if (productCount >= products.length) {
            System.out.println("新增失敗：商品陣列已滿 (最大容量 10 項)！");
            return;
        }
        if (findProductIndex(name) != -1) {
            System.out.println("新增失敗：商品名稱「" + name.trim() + "」已存在！");
            return;
        }
        if (price < 0 || stock < 0) {
            System.out.println("新增失敗：價格或庫存不能為負數！");
            return;
        }

        products[productCount] = new Product(name, price, stock);
        productCount++;
        addedProducts++;
        System.out.println("商品「" + name.trim() + "」新增成功！");
    }

    private static boolean sellProduct(int index, int qty) {
        if (qty <= 0) {
            System.out.println("出售失敗：數量必須大於 0。");
            return false;
        }
        Product p = products[index];
        if (p.getStock() < qty) {
            System.out.println("出售失敗：「" + p.getName() + "」庫存不足！剩餘庫存: " + p.getStock());
            return false;
        }
        p.setStock(p.getStock() - qty);
        totalSales += qty;
        return true;
    }

    private static boolean restockProduct(int index, int qty) {
        if (qty <= 0) {
            System.out.println("補貨失敗：數量必須大於 0。");
            return false;
        }
        Product p = products[index];
        p.setStock(p.getStock() + qty);
        totalRestocks += qty;
        return true;
    }

    private static boolean updatePrice(int index, int newPrice) {
        if (newPrice < 0) {
            System.out.println("修改失敗：價格不能為負數。");
            return false;
        }
        products[index].setPrice(newPrice);
        priceUpdates++;
        return true;
    }

    private static void showAllProducts() {
        System.out.println("\n--- 目前全商品清單 ---");
        for (int i = 0; i < productCount; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
    }

    private static void searchProduct() {
        System.out.print("請輸入要搜尋的完整商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(name);
        if (index != -1) {
            System.out.println("找到商品 -> " + products[index]);
        } else {
            System.out.println("找不到名稱為「" + name.trim() + "」的商品。");
        }
    }

    private static void addProductMenu() {
        System.out.print("請輸入新商品名稱: ");
        String name = scanner.nextLine();
        int price = getIntInput("請輸入商品價格: ");
        int stock = getIntInput("請輸入商品初始庫存: ");
        addProduct(name, price, stock);
    }

    private static void sellProductMenu() {
        System.out.print("請輸入要出售的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("找不到該商品，無法出售。");
            return;
        }
        int qty = getIntInput("請輸入出售數量: ");
        if (sellProduct(index, qty)) {
            System.out.println("出售成功！");
        }
    }

    private static void restockProductMenu() {
        System.out.print("請輸入要補貨的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("找不到該商品，無法補貨。");
            return;
        }
        int qty = getIntInput("請輸入補貨數量: ");
        if (restockProduct(index, qty)) {
            System.out.println("補貨成功！");
        }
    }

    private static void updatePriceMenu() {
        System.out.print("請輸入要修改價格的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("找不到該商品，無法修改價格。");
            return;
        }
        int newPrice = getIntInput("請輸入新價格: ");
        if (updatePrice(index, newPrice)) {
            System.out.println("價格修改成功！");
        }
    }

    private static void showLowStockProducts() {
        System.out.println("\n--- 低庫存警戒商品 (庫存 <= 5) ---");
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i].getStock() <= 5) {
                System.out.println(products[i]);
                found = true;
            }
        }
        if (!found) System.out.println("目前沒有商品處於低庫存狀態。");
    }

    private static void showTotalValue() {
        int totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            totalValue += products[i].getValue();
        }
        System.out.println("\n目前全倉庫內商品總價值為: " + totalValue + " 元");
    }

    private static void showSummary() {
        System.out.println("\n====================================");
        System.out.println("  系統已結束。今日操作摘要統計如下：  ");
        System.out.println("====================================");
        System.out.println("1. 新增商品品項數: " + addedProducts + " 項");
        System.out.println("2. 商品出售總件數: " + totalSales + " 件");
        System.out.println("3. 商品補充總件數: " + totalRestocks + " 件");
        System.out.println("4. 修改價格變更次數: " + priceUpdates + " 次");
        System.out.println("====================================");
        System.out.println("感謝使用商品管理系統，再見！");
    }
}
