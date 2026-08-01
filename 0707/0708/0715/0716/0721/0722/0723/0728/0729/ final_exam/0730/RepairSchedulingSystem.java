import java.util.*;

public class RepairSchedulingSystem {

    private List<RepairTask> allTasks = new ArrayList<>();     
    private Queue<RepairTask> pendingQueue = new LinkedList<>(); 
    private Stack<RepairTask> completedStack = new Stack<>();  

    public void addTask(RepairTask task) {
        allTasks.add(task);
        pendingQueue.offer(task);
    }

    public RepairTask completeTask() {
        if (pendingQueue.isEmpty()) return null;
        RepairTask task = pendingQueue.poll();
        completedStack.push(task);
        return task;
    }

    public RepairTask undoCompletion() {
        if (completedStack.isEmpty()) return null;
        RepairTask task = completedStack.pop();
        ((LinkedList<RepairTask>) pendingQueue).addFirst(task);
        return task;
    }

    public void printStatistics() {
        System.out.println("\n--- 工作統計 ---");
        System.out.println("全部工作數: " + allTasks.size());
        System.out.println("等待中工作數 (Queue): " + pendingQueue.size());
        System.out.println("已完成工作數 (Stack): " + completedStack.size());
    }

    public static void main(String[] args) {
        RepairSchedulingSystem sys = new RepairSchedulingSystem();

        // 登記工作 (相同優先度測試穩定性)
        sys.addTask(new RepairTask("T001", "Server A", 3, 1));
        sys.addTask(new RepairTask("T002", "Router B", 5, 2));
        sys.addTask(new RepairTask("T003", "Switch C", 5, 3));
        sys.addTask(new RepairTask("T004", "Server A", 1, 4));

        System.out.println("=== 1. Merge Sort 依優先等級降冪排序 ===");
        RepairAlgorithms.sortByPriorityDescending(sys.allTasks, 0, sys.allTasks.size() - 1);
        sys.allTasks.forEach(System.out::println);

        System.out.println("\n=== 2. 搜尋功能測試 ===");
        System.out.println("搜尋 ID T002: " + RepairAlgorithms.searchById(sys.allTasks, "T002"));
        System.out.println("搜尋設備 Server A: " + RepairAlgorithms.searchByDeviceName(sys.allTasks, "Server A"));

        System.out.println("\n=== 3. 處理與復原機制測試 ===");
        System.out.println("完成工作: " + sys.completeTask());
        sys.printStatistics();

        System.out.println("\n執行復原...");
        System.out.println("復原工作: " + sys.undoCompletion());
        sys.printStatistics();
    }
}
