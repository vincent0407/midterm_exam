import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeliveryProcessingSystem {

    private Queue<DeliveryTask> pendingQueue; 
    private Stack<DeliveryTask> completedStack; 

    public DeliveryProcessingSystem() {
        this.pendingQueue = new LinkedList<>();
        this.completedStack = new Stack<>();
    }

    public void addTask(String taskId, String destination, String recipient) {
        DeliveryTask task = new DeliveryTask(taskId, destination, recipient);
        pendingQueue.offer(task);
        System.out.println("【新增工作】已加入 Queue: " + task);
    }

    public void completeNextTask() {
        if (pendingQueue.isEmpty()) {
            System.out.println("【提示】目前沒有待配送的工作！");
            return;
        }
        DeliveryTask task = pendingQueue.poll();
        completedStack.push(task);
        System.out.println("【完成工作】已配送完畢: " + task);
    }

    // 3. 查看下一筆待配送工作
    public void peekNextTask() {
        if (pendingQueue.isEmpty()) {
            System.out.println("【查看下一筆】目前 Queue 為空，無待處理工作。");
        } else {
            System.out.println("【查看下一筆】即將處理: " + pendingQueue.peek());
        }
    }

    public void undoLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("【提示】沒有已完成的紀錄可以復原！");
            return;
        }
        DeliveryTask task = completedStack.pop();
        pendingQueue.offer(task); 
        System.out.println("【復原成功】已將 " + task + " 重置並重新放回待配送 Queue 尾端！");
    }

    public void showStatus() {
        System.out.println("\n================ 即時狀態統計 ================");
        System.out.println("待配送數量 (Queue size): " + pendingQueue.size());
        System.out.println("已完成數量 (Stack size): " + completedStack.size());

        System.out.println("\n--- 待配送清單 (Queue 順序) ---");
        if (pendingQueue.isEmpty()) {
            System.out.println("  (無)");
        } else {
            for (DeliveryTask task : pendingQueue) {
                System.out.println("  - " + task);
            }
        }

        System.out.println("\n--- 完成紀錄 (Stack 順序: 由最新到最舊) ---");
        if (completedStack.isEmpty()) {
            System.out.println("  (無)");
        } else {
            for (int i = completedStack.size() - 1; i >= 0; i--) {
                System.out.println("  - " + completedStack.get(i));
            }
        }
        System.out.println("==============================================\n");
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("=== 配送工作流程系統測試 ===\n");

        system.completeNextTask();
        system.undoLastCompleted();

        System.out.println();

        system.addTask("T001", "台北市信義區", "張先生");
        system.addTask("T002", "新北市板橋區", "李小姐");
        system.addTask("T003", "台中市西屯區", "王先生");

        system.peekNextTask();

        system.showStatus();

        system.completeNextTask();
        system.completeNextTask();

    
        system.showStatus();

        system.undoLastCompleted();

        system.addTask("T004", "高雄市左營區", "趙先生");

        system.showStatus();
    }
}
