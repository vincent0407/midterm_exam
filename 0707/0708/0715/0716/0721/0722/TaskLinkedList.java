public class TaskLinkedList {
    private TaskNode head;

    public TaskLinkedList() {
        this.head = null;
    }

    public boolean containsId(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addUrgentTask(String id, String description) {
        if (containsId(id)) {
            System.out.println("【新增失敗】工作代碼已存在: " + id);
            return false;
        }
        TaskNode newNode = new TaskNode(id, description);
        newNode.next = head;
        head = newNode;
        System.out.println("新增緊急工作 (前端): [" + id + "] " + description);
        return true;
    }

    public boolean addNormalTask(String id, String description) {
        if (containsId(id)) {
            System.out.println("【新增失敗】工作代碼已存在: " + id);
            return false;
        }
        TaskNode newNode = new TaskNode(id, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("新增一般工作 (尾端): [" + id + "] " + description);
        return true;
    }

    public boolean completeTask(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                current.isCompleted = true;
                System.out.println("成功完成工作: [" + id + "] " + current.description);
                return true;
            }
            current = current.next;
        }
        System.out.println("【標記失敗】找不到工作代碼: " + id);
        return false;
    }

    public boolean removeTask(String id) {
        if (head == null) {
            System.out.println("【刪除失敗】工作清單為空！");
            return false;
        }

        if (head.id.equals(id)) {
            System.out.println("成功刪除工作: [" + head.id + "] " + head.description);
            head = head.next;
            return true;
        }

        TaskNode current = head;
        while (current.next != null && !current.next.id.equals(id)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("【刪除失敗】找不到工作代碼: " + id);
            return false;
        }

        System.out.println("成功刪除工作: [" + current.next.id + "] " + current.next.description);
        current.next = current.next.next;
        return true;
    }

    public void printPendingTasks() {
        System.out.println("\n--- 未完成工作清單 ---");
        if (head == null) {
            System.out.println("[ 清單為空 ]");
            return;
        }

        TaskNode current = head;
        boolean hasPending = false;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println("代碼: " + current.id + " | 說明: " + current.description);
                hasPending = true;
            }
            current = current.next;
        }

        if (!hasPending) {
            System.out.println("(所有工作皆已完成！)");
        }
        System.out.println("-----------------------");
    }

    public void printTaskStats() {
        int total = 0;
        int pending = 0;

        TaskNode current = head;
        while (current != null) {
            total++;
            if (!current.isCompleted) {
                pending++;
            }
            current = current.next;
        }

        System.out.println("\n[ 工作統計 ]");
        System.out.println("工作總數：" + total);
        System.out.println("未完成數量：" + pending);
        System.out.println("已完成數量：" + (total - pending) + "\n");
    }
}
