class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListSearchRemove {

    public static boolean contains(Node head, int target) {
        Node current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static Node removeValue(Node head, int target) {
         if (head == null) {
            System.out.println("【刪除失敗】串列為空，無法進行刪除。");
            return null;
        }

        if (head.data == target) {
            System.out.println("成功刪除 head 節點: " + target);
            return head.next;
        }

        Node current = head;
        while (current.next != null && current.next.data != target) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("【刪除失敗】找不到資料: " + target);
            return head;
        }

        System.out.println("成功刪除節點: " + target);
        current.next = current.next.next;

        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("目前串列：[ Empty ]");
            return;
        }
        System.out.print("目前串列：");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("=== 初始狀態 ===");
        printList(head);

        System.out.println("\n--- 測試搜尋 (contains) ---");
        System.out.println("是否包含 20? " + contains(head, 20));
        System.out.println("是否包含 99? " + contains(head, 99));

        System.out.println("\n--- 1. 測試刪除中間節點 (30) ---");
        head = removeValue(head, 30);
        printList(head);

        System.out.println("\n--- 2. 測試刪除 head 節點 (10) ---");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("\n--- 3. 測試刪除最後一個節點 (40) ---");
        head = removeValue(head, 40);
        printList(head);

        System.out.println("\n--- 4. 測試刪除找不到的資料 (99) ---");
        head = removeValue(head, 99);
        printList(head);

        System.out.println("\n--- 5. 測試清空後刪除 (空串列) ---");
        head = removeValue(head, 20); // 刪除最後剩下的 20
        printList(head);
        head = removeValue(head, 5);  // 測試空串列刪除
        printList(head);
    }
}
