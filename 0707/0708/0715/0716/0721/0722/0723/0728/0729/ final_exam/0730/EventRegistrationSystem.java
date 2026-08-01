import java.util.*;

public class EventRegistrationSystem {

    private final int capacity;
    private List<Registration> mainRegistrations = new ArrayList<>(); // 成功報名者
    private Queue<Registration> waitlistQueue = new LinkedList<>();     // 候補佇列
    private Stack<Registration> cancelStack = new Stack<>();            // 最近取消記錄

    public EventRegistrationSystem(int capacity) {
        this.capacity = capacity;
    }

    public boolean register(Registration reg) {
        for (Registration r : mainRegistrations) {
            if (r.getRegId().equals(reg.getRegId())) {
                System.out.println("[錯誤] 重複的報名編號: " + reg.getRegId());
                return false;
            }
        }
        for (Registration r : waitlistQueue) {
            if (r.getRegId().equals(reg.getRegId())) {
                System.out.println("[錯誤] 重複的報名編號 (已在候補區): " + reg.getRegId());
                return false;
            }
        }

        if (mainRegistrations.size() < capacity) {
            mainRegistrations.add(reg);
            System.out.println("[成功] 正取報名成功: " + reg);
        } else {
            waitlistQueue.offer(reg);
            System.out.println("[提示] 名額已滿，進入候補佇列: " + reg);
        }
        return true;
    }

    public boolean cancelRegistration(String regId) {
        Registration target = null;
        for (Registration r : mainRegistrations) {
            if (r.getRegId().equals(regId)) {
                target = r;
                break;
            }
        }

        if (target == null) {
            System.out.println("[錯誤] 取消失敗，找不到該報名資料: " + regId);
            return false;
        }

        mainRegistrations.remove(target);
        cancelStack.push(target);
        System.out.println("[成功] 取消報名: " + target);

        if (!waitlistQueue.isEmpty()) {
            Registration promoted = waitlistQueue.poll();
            mainRegistrations.add(promoted);
            System.out.println("[遞補成功] 候補學員遞補正取: " + promoted);
        }
        return true;
    }

    public List<Registration> getMainRegistrations() { return mainRegistrations; }

    public static void main(String[] args) {
        EventRegistrationSystem sys = new EventRegistrationSystem(2); // 容量上限 2

        System.out.println("=== 1. 報名測試（正取與額滿候補） ===");
        sys.register(new Registration("R002", "Alice"));
        sys.register(new Registration("R001", "Bob"));
        sys.register(new Registration("R003", "Charlie")); // 額滿進 Queue

        System.out.println("\n=== 2. 重複報名測試 ===");
        sys.register(new Registration("R001", "David"));

        System.out.println("\n=== 3. 排序與搜尋 ===");
        RegistrationAlgorithms.sortById(sys.getMainRegistrations(), 0, sys.getMainRegistrations().size() - 1);
        System.out.println("正取清單 (Merge Sort 排序後): " + sys.getMainRegistrations());
        System.out.println("Binary Search 搜尋 R001: " + RegistrationAlgorithms.binarySearchById(sys.getMainRegistrations(), "R001"));
        System.out.println("Sequential Search 搜尋 Alice: " + RegistrationAlgorithms.sequentialSearchByName(sys.getMainRegistrations(), "Alice"));

        System.out.println("\n=== 4. 取消與候補自動遞補 ===");
        sys.cancelRegistration("R002");
        System.out.println("最新正取清單: " + sys.getMainRegistrations());

        System.out.println("\n=== 5. 取消不存在資料測試 ===");
        sys.cancelRegistration("R999");
    }
}
