public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList system = new TaskLinkedList();

        System.out.println("=== 1. 新增一般與緊急工作 ===");
        system.addNormalTask("T001", "撰寫 Java 課後作業");
        system.addNormalTask("T002", "準備微積分小考");
        system.addUrgentTask("T000", "回覆緊急 E-mail"); 
        system.addNormalTask("T003", "整理筆記");

        system.printPendingTasks();
        system.printTaskStats();

        System.out.println("=== 2. 完成特定工作 ===");
        system.completeTask("T000"); 
        system.completeTask("T002"); 

        system.printPendingTasks();
        system.printTaskStats();

        System.out.println("=== 3. 測試刪除工作 ===");
        system.removeTask("T001"); 
        system.removeTask("T999"); 

        system.printPendingTasks();
        system.printTaskStats();

        System.out.println("=== 4. 測試空串列邊界條件 ===");
        system.removeTask("T000");
        system.removeTask("T003"); 
        
        system.printPendingTasks();
        system.printTaskStats();
        system.removeTask("T001"); 
     }
}