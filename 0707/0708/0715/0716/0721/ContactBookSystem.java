import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {

    private static final ArrayList<Contact> contactList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addContact();
                case "2" -> searchContact();
                case "3" -> updatePhone();
                case "4" -> deleteContact();
                case "5" -> listAllContacts();
                case "6" -> {
                    System.out.println("系統已結束，謝謝使用！");
                    running = false;
                }
                default -> System.out.println("❌ 錯誤：請輸入有效的選項 (1-6)！");
            }
            System.out.println();
        }
    }

    public static void printMenu() {
        System.out.println("==================================");
        System.out.println("        聯絡人管理系統            ");
        System.out.println("==================================");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 列出完整清單");
        System.out.println("6. 離開系統");
        System.out.println("==================================");
    }

    public static void addContact() {
        System.out.print("請輸入代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("❌ 錯誤：代碼不可為空白！");
            return;
        }
        if (findContactById(id) != null) {
            System.out.println("❌ 錯誤：代碼 \"" + id + "\" 已存在，不可重複！");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("❌ 錯誤：空白姓名不可加入！");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入 Email: ");
        String email = scanner.nextLine().trim();

        contactList.add(new Contact(id, name, phone, email));
        System.out.println("✅ 成功新增聯絡人: " + name);
    }

    public static void searchContact() {
        System.out.print("請輸入要搜尋的代碼或姓名: ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("❌ 錯誤：關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (Contact c : contactList) {
            if (c.getId().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                System.out.println("🔍 找到聯絡人: " + c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("⚠️ 找不到符合 \"" + keyword + "\" 的聯絡人。");
        }
    }

    public static void updatePhone() {
        System.out.print("請輸入要修改電話的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact c = findContactById(id);
        if (c == null) {
            System.out.println("❌ 錯誤：找不到代碼為 \"" + id + "\" 的聯絡人！");
            return;
        }

        System.out.print("請輸入新電話: ");
        String newPhone = scanner.nextLine().trim();
        c.setPhone(newPhone);
        System.out.println("✅ 成功修改 " + c.getName() + " 的電話為: " + newPhone);
    }

    public static Contact findContactById(String id) {
        if (id.isEmpty()) return null;
        for (Contact c : contactList) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public static void deleteContact() {
        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        Contact c = findContactById(id);
        if (c != null) {
            contactList.remove(c);
            System.out.println("✅ 成功刪除聯絡人: " + c.getName());
        } else {
            System.out.println("❌ 刪除失敗：找不到代碼為 \"" + id + "\" 的聯絡人。");
        }
    }

    public static void listAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println("📋 目前聯絡人清單為空。");
            return;
        }
        System.out.println("📋 完整聯絡人清單：");
        for (Contact c : contactList) {
            System.out.println(c);
        }
    }
}
