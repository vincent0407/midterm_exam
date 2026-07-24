class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class NumberHistoryList {
    private Node head;

    public NumberHistoryList() {
        this.head = null;
    }

    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        System.out.println("前端新增: " + value);
    }

    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("尾端新增: " + value);
    }

    public boolean search(int target) {
        Node current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean remove(int value) {
        if (head == null) {
            System.out.println("【刪除失敗】串列為空，找不到: " + value);
            return false;
        }

        // 刪除 head
        if (head.data == value) {
            head = head.next;
            System.out.println("成功刪除: " + value);
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("【刪除失敗】找不到資料: " + value);
            return false;
        }

        current.next = current.next.next;
        System.out.println("成功刪除: " + value);
        return true;
    }

    public void printList() {
        if (head == null) {
            System.out.println("串列內容：[ 空串列 ]");
            return;
        }
        System.out.print("串列內容：");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public void printStats() {
        if (head == null) {
            System.out.println("\n[ 統計資訊 ]");
            System.out.println("串列狀態：空串列");
            System.out.println("Size: 0, 總和: 0, 最大值: 無, 最小值: 無\n");
            return;
        }

        int count = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        Node current = head;
        while (current != null) {
            count++;
            sum += current.data;
            if (current.data > max) max = current.data;
            if (current.data < min) min = current.data;
            current = current.next;
        }

        System.out.println("\n[ 統計資訊 ]");
        System.out.println("Size: " + count);
        System.out.println("總和: " + sum);
        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min + "\n");
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("=== 開始進行測試操作 (至少 8 次操作) ===");

        list.printStats();

        list.addFirst(20);

        list.addLast(50);

        list.addFirst(10);

        list.addLast(30);
        list.printList();
        list.printStats();

        System.out.println("搜尋 50: " + (list.search(50) ? "存在" : "不存在"));
        System.out.println("搜尋 99: " + (list.search(99) ? "存在" : "不存在"));

        list.remove(10);
        list.remove(99);
        list.printList();

        list.remove(30);
        list.printList();
        list.printStats();
    }
}
