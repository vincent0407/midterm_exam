import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {

    private static final ArrayList<CartItem> cart = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addItem();
                case "2" -> updateQuantity();
                case "3" -> removeItem();
                case "4" -> displayCart();
                case "5" -> {
                    System.out.println("系統已結束，謝謝使用！");
                    running = false;
                }
                default -> System.out.println("❌ 錯誤：請輸入有效的選項 (1-5)！");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("==================================");
        System.out.println("          購物車管理系統          ");
        System.out.println("==================================");
        System.out.println("1. 加入商品至購物車");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 查看購物車與計算總額");
        System.out.println("5. 離開系統");
        System.out.println("==================================");
    }

    private static void addItem() {
        System.out.print("請輸入商品代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("❌ 錯誤：商品代碼不可為空白！");
            return;
        }

        System.out.print("請輸入要加入的數量: ");
        int qty = readIntInput();
        if (qty <= 0) {
            System.out.println("❌ 錯誤：數量必須大於 0！");
            return;
        }

        CartItem existingItem = findItemById(id);
        
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + qty);
            System.out.println("✅ 商品已存在，已自動累加數量。目前總數: " + existingItem.getQuantity());
        } else {
            System.out.print("請輸入商品名稱: ");
            String name = scanner.nextLine().trim();

            System.out.print("請輸入商品單價: ");
            double price = readDoubleInput();
            if (price < 0) {
                System.out.println("❌ 錯誤：單價不可為負數！");
                return;
            }

            cart.add(new CartItem(id, name, price, qty));
            System.out.println("✅ 成功新增商品至購物車！");
        }
    }

    private static void updateQuantity() {
        System.out.print("請輸入要修改數量的商品代碼: ");
        String id = scanner.nextLine().trim();

        CartItem item = findItemById(id);
        if (item == null) {
            System.out.println("❌ 錯誤：購物車中找不到代碼為 \"" + id + "\" 的商品！");
            return;
        }

        System.out.print("請輸入新的數量: ");
        int newQty = readIntInput();

        if (newQty <= 0) {
            System.out.println("❌ 錯誤：數量小於或等於 0 時不接受更新！");
            return;
        }

        item.setQuantity(newQty);
        System.out.println("✅ 成功更新商品數量為: " + newQty);
    }

    private static void removeItem() {
        System.out.print("請輸入要移除的商品代碼: ");
        String id = scanner.nextLine().trim();

        CartItem item = findItemById(id);
        if (item != null) {
            cart.remove(item);
            System.out.println("✅ 成功將商品 \"" + item.getName() + "\" 從購物車中移除。");
        } else {
            System.out.println("❌ 移除失敗：找不到代碼為 \"" + id + "\" 的商品。");
        }
    }

    private static void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("🛒 購物車目前是空的。");
            return;
        }

        System.out.println("🛒 購物車明細：");
        double total = 0;
        for (CartItem item : cart) {
            System.out.println(item);
            total += item.getSubtotal();
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("💰 購物車總金額: $%.2f\n", total);
    }

    private static CartItem findItemById(String id) {
        for (CartItem item : cart) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    private static int readIntInput() {
        try {
            int val = Integer.parseInt(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double readDoubleInput() {
        try {
            double val = Double.parseDouble(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }
}
