import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {

    private static final ArrayList<String> nameList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addName();
                case "2" -> searchName();
                case "3" -> updateName();
                case "4" -> deleteName();
                case "5" -> listAllNames();
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
        System.out.println("        名單管理系統 (NameList)    ");
        System.out.println("==================================");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部姓名");
        System.out.println("6. 離開系統");
        System.out.println("==================================");
    }

    private static void addName() {
        System.out.print("請輸入要新增的姓名: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("❌ 錯誤：不得加入空白姓名！");
            return;
        }

        nameList.add(name);
        System.out.println("✅ 成功新增姓名: " + name);
    }

    private static void searchName() {
        System.out.print("請輸入要搜尋的姓名: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("❌ 錯誤：搜尋關鍵字不能為空白！");
            return;
        }

        boolean found = false;
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(name)) {
                System.out.println("🔍 找到符合紀錄：索引 [" + i + "] " + nameList.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("⚠️ 找不到名稱為 \"" + name + "\" 的紀錄。");
        }
    }

    private static void updateName() {
        System.out.print("請輸入要修改的舊姓名: ");
        String oldName = scanner.nextLine().trim();

        int index = findIndexIgnoreCase(oldName);
        if (index == -1) {
            System.out.println("❌ 修改失敗：找不到姓名 \"" + oldName + "\"。");
            return;
        }

        System.out.print("請輸入新姓名: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("❌ 錯誤：新姓名不得為空白！");
            return;
        }

        String originalName = nameList.get(index);
        nameList.set(index, newName);
        System.out.println("✅ 成功將 \"" + originalName + "\" 修改為 \"" + newName + "\"。");
    }

    private static void deleteName() {
        System.out.print("請輸入要刪除的姓名: ");
        String nameToDelete = scanner.nextLine().trim();

        int index = findIndexIgnoreCase(nameToDelete);
        if (index != -1) {
            String removedName = nameList.remove(index);
            System.out.println("✅ 成功刪除姓名: " + removedName);
        } else {
            System.out.println("❌ 刪除失敗：找不到姓名 \"" + nameToDelete + "\"。");
        }
    }

    private static void listAllNames() {
        if (nameList.isEmpty()) {
            System.out.println("📋 目前名單為空。");
            return;
        }

        System.out.println("📋 目前所有名單列表：");
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println((i + 1) + ". " + nameList.get(i));
        }
    }

    private static int findIndexIgnoreCase(String target) {
        if (target.isEmpty()) return -1;
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}
