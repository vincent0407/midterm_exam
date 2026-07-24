class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class BuildLinkedList {
    public static void main(String[] args) {
        // 1. 建立 10、20、30、40 四個 Node
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        Node head = n1;

        traverseAndCalculate(head);

        System.out.println("\n--- 測試空串列狀況 ---");
        Node emptyHead = null;
        traverseAndCalculate(emptyHead);
    }

    public static void traverseAndCalculate(Node head) {
        if (head == null) {
            System.out.println("警告：此串列為空串列 (Empty List)！");
            System.out.println("總數量: 0，總和: 0");
            return;
        }

        Node current = head;
        int count = 0;
        int sum = 0;

        System.out.print("鏈結串列內容：");

        // 走訪串列，確保鏈結不遺失
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            sum += current.data;
            count++;
            current = current.next; 
        }

        System.out.println();
        System.out.println("節點總數：" + count);
        System.out.println("節點總和：" + sum);
    }
}
