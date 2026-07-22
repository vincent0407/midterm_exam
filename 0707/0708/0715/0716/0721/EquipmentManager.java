import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {

    private static final ArrayList<Equipment> equipmentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addEquipment();
                case "2" -> searchEquipmentById();
                case "3" -> borrowEquipment();
                case "4" -> returnEquipment();
                case "5" -> listAvailableEquipment();
                case "6" -> {
                    System.out.println("系統已結束，謝謝使用！");
                    running = false;
                }
                default -> System.out.println("❌ 錯誤：請輸入有效的選項 (1-6)！");
            }
            System.out.println();
        }
    }


    private static void printMenu() {
        System.out.println("==================================");
        System.out.println("        設備物件集合管理系統        ");
        System.out.println("==================================");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋設備");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出所有可借用設備");
        System.out.println("6. 離開系統");
        System.out.println("==================================");
    }

    private static void addEquipment() {
        System.out.print("請輸入設備代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("❌ 錯誤：設備代碼不得為空白！");
            return;
        }

        if (findEquipmentById(id) != null) {
            System.out.println("❌ 錯誤：設備代碼 \"" + id + "\" 已存在，不可重複新增！");
            return;
        }

        System.out.print("請輸入設備名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("❌ 錯誤：設備名稱不得為空白！");
            return;
        }

        Equipment equipment = new Equipment(id, name);
        equipmentList.add(equipment);
        System.out.println("✅ 成功新增設備: " + equipment.getName() + " (代碼: " + id + ")");
    }

    private static void searchEquipmentById() {
        System.out.print("請輸入要搜尋的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq != null) {
            System.out.println("🔍 搜尋結果：");
            System.out.println(eq);
        } else {
            System.out.println("⚠️ 找不到代碼為 \"" + id + "\" 的設備。");
        }
    }

    private static void borrowEquipment() {
        System.out.print("請輸入要借出的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq == null) {
            System.out.println("❌ 借出失敗：找不到代碼為 \"" + id + "\" 的設備。");
            return;
        }

        if (!eq.isAvailable()) {
            System.out.println("⚠️ 設備 \"" + eq.getName() + "\" 目前已被借出，無法再次借出！");
        } else {
            eq.setAvailable(false);
            System.out.println("✅ 成功借出設備: " + eq.getName());
        }
    }


    private static void returnEquipment() {
        System.out.print("請輸入要歸還的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq == null) {
            System.out.println("❌ 歸還失敗：找不到代碼為 \"" + id + "\" 的設備。");
            return;
        }

        if (eq.isAvailable()) {
            System.out.println("⚠️ 設備 \"" + eq.getName() + "\" 目前並未被借出，無需歸還！");
        } else {
            eq.setAvailable(true);
            System.out.println("✅ 成功歸還設備: " + eq.getName());
        }
    }

    private static void listAvailableEquipment() {
        System.out.println("📋 可借用設備列表：");
        boolean hasAvailable = false;

        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                hasAvailable = true;
            }
        }

        if (!hasAvailable) {
            System.out.println("目前沒有可借用的設備。");
        }
    }

    private static Equipment findEquipmentById(String id) {
        if (id.isEmpty()) return null;
        for (Equipment eq : equipmentList) {
            if (eq.getId().equalsIgnoreCase(id)) {
                return eq;
            }
        }
        return null;
    }
}
