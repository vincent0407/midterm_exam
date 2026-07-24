class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListReverse {

    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next; 
            current.next = prev; 
            prev = current;      
            current = next;     
        }

        return prev;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("[ Empty ]");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("--- 測試多節點反轉 ---");
        Node headMulti = new Node(10);
        headMulti.next = new Node(20);
        headMulti.next.next = new Node(30);
        headMulti.next.next.next = new Node(40);

        System.out.print("反轉前：");
        printList(headMulti);
        headMulti = reverse(headMulti);
        System.out.print("反轉後：");
        printList(headMulti);

        System.out.println("\n--- 測試單一節點反轉 ---");
        Node headSingle = new Node(50);

        System.out.print("反轉前：");
        printList(headSingle);
        headSingle = reverse(headSingle);
        System.out.print("反轉後：");
        printList(headSingle);

        System.out.println("\n--- 測試空串列反轉 ---");
        Node headEmpty = null;

        System.out.print("反轉前：");
        printList(headEmpty);
        headEmpty = reverse(headEmpty);
        System.out.print("反轉後：");
        printList(headEmpty);
    }
}
