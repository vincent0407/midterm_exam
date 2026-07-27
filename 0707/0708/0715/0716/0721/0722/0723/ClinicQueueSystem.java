import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class ClinicQueueSystem {

    private Queue<Patient> queue;
    private Set<Integer> usedIds; 
    private int totalServedCount; 

    public ClinicQueueSystem() {
        this.queue = new LinkedList<>();
        this.usedIds = new HashSet<>();
        this.totalServedCount = 0;
    }

    public boolean register(int id, String name, String department) {
        if (usedIds.contains(id)) {
            System.out.println("【掛號失敗】號碼 " + id + " 已存在，不可以重複！");
            return false;
        }
        Patient patient = new Patient(id, name, department);
        usedIds.add(id);
        queue.offer(patient);
        System.out.println("【掛號成功】" + patient);
        return true;
    }

    public void callPatient() {
        if (queue.isEmpty()) {
            System.out.println("【叫號失敗】目前沒有等待中的病患！");
            return;
        }
        Patient patient = queue.poll();
        totalServedCount++;
        System.out.println("【診所叫號】請 " + patient + " 至診間就診。");
    }

    public void showQueueStatus() {
        System.out.println("\n----------------------------------------");
        if (queue.isEmpty()) {
            System.out.println("【等待狀態】目前無病患等待。");
        } else {
            System.out.println("下一位看診病患: " + queue.peek());
            System.out.println("完整等待清單:");
            for (Patient p : queue) {
                System.out.println(" - " + p);
            }
        }
        System.out.println("----------------------------------------");
    }

    public void showStatistics() {
        Map<String, Integer> deptCountMap = new HashMap<>();
        for (Patient p : queue) {
            deptCountMap.put(p.getDepartment(), deptCountMap.getOrDefault(p.getDepartment(), 0) + 1);
        }

        System.out.println("\n===== 診所即時統計資訊 =====");
        System.out.println("各科別等待人數:");
        if (deptCountMap.isEmpty()) {
            System.out.println("  (無人等待)");
        } else {
            for (Map.Entry<String, Integer> entry : deptCountMap.entrySet()) {
                System.out.println("  - " + entry.getKey() + ": " + entry.getValue() + " 人");
            }
        }
        System.out.println("當前累積總服務人數: " + totalServedCount + " 人");
        System.out.println("============================");
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("=== 診所叫號系統測試 ===\n");

        // 掛號測試
        clinic.register(101, "王小明", "內科");
        clinic.register(102, "李大華", "眼科");
        clinic.register(103, "張阿姨", "內科");
        clinic.register(104, "陳先生", "皮膚科");

    
        clinic.register(101, "重複測試者", "內科");

        clinic.showQueueStatus();
        clinic.showStatistics();

        System.out.println("\n--- 開始叫號看診 ---");
        clinic.callPatient();
        clinic.callPatient();

        clinic.register(105, "林小姐", "眼科");

        clinic.showQueueStatus();
        clinic.showStatistics();
    }
}
