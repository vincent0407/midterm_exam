import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchSystem {

    public static List<Integer> binarySearchAll(Employee[] employees, String targetId) {
        List<Integer> results = new ArrayList<>();
        if (employees == null || employees.length == 0) {
            return results; 
        }

        int low = 0;
        int high = employees.length - 1;
        int foundIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = employees[mid].getId().compareTo(targetId);

            if (cmp == 0) {
                foundIndex = mid;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (foundIndex != -1) {
            int left = foundIndex;
            while (left >= 0 && employees[left].getId().equals(targetId)) {
                left--;
            }
            int right = foundIndex;
            while (right < employees.length && employees[right].getId().equals(targetId)) {
                right++;
            }
        
            for (int i = left + 1; i < right; i++) {
                results.add(i);
            }
        }

        return results;
    }

    public static void searchAndPrint(Employee[] employees, String targetId) {
        System.out.println("\n搜尋目標編號: " + targetId);
        
        if (employees == null || employees.length == 0) {
            System.out.println("【錯誤】員工陣列為空，無法進行搜尋！");
            return;
        }

        List<Integer> indices = binarySearchAll(employees, targetId);

        if (indices.isEmpty()) {
            System.out.println("【訊息】查無此員工編號 (" + targetId + ")");
        } else {
            System.out.println("【找到結果】包含 " + indices.size() + " 筆符合資料：");
            for (int idx : indices) {
                System.out.println("  索引 [" + idx + "]: " + employees[idx]);
            }
        }
    }

    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E001", "張三", "資訊部", "101"),
            new Employee("E002", "李四", "業務部", "102"),
            new Employee("E003", "王五", "研發部", "103"),
            new Employee("E003", "王五(兼職)", "顧問部", "104"), 
            new Employee("E005", "趙六", "財務部", "105")
        };

        // 測試 1: 正常查詢
        searchAndPrint(employees, "E002");

        // 測試 2: 重複編號查詢
        searchAndPrint(employees, "E003");

        // 測試 3: 查無資料
        searchAndPrint(employees, "E999");

        // 測試 4: 空陣列查詢
        searchAndPrint(new Employee[]{}, "E001");
    }
}
