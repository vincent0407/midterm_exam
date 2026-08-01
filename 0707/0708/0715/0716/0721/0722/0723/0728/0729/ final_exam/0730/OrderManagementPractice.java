import java.util.*;

public class OrderManagementPractice {

    private Map<String, Order> mainStorage = new HashMap<>(); 
    private Queue<Order> pendingQueue = new LinkedList<>();  
    private Stack<Order> completedStack = new Stack<>();     

    public boolean addOrder(Order order) {
        if (mainStorage.containsKey(order.getOrderId())) {
            System.out.println("[錯誤] 訂單編號已重複，無法新增: " + order.getOrderId());
            return false;
        }
        mainStorage.put(order.getOrderId(), order);
        pendingQueue.offer(order);
        System.out.println("[成功] 已加入待處理佇列: " + order);
        return true;
    }

    public Order peekNextPendingOrder() {
        if (pendingQueue.isEmpty()) {
            System.out.println("[提示] 目前沒有待處理訂單 (Queue 為空)");
            return null;
        }
        return pendingQueue.peek();
    }

    public Order processNextOrder() {
        if (pendingQueue.isEmpty()) {
            System.out.println("[提示] 沒有可處理的訂單 (Queue 為空)");
            return null;
        }
        Order processed = pendingQueue.poll();
        completedStack.push(processed);
        System.out.println("[處理完成] 訂單已移至 Stack: " + processed);
        return processed;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(mainStorage.values());
    }

    public static void main(String[] args) {
        OrderManagementPractice sys = new OrderManagementPractice();

        System.out.println("=== 1. 測試邊界情況：空 Queue & 空 Stack ===");
        sys.peekNextPendingOrder();
        sys.processNextOrder();

        System.out.println("\n=== 2. 新增訂單與防止重複編號 ===");
        sys.addOrder(new Order("O001", "Alice", 1500.0));
        sys.addOrder(new Order("O002", "Bob", 3200.0));
        sys.addOrder(new Order("O003", "Alice", 800.0));
        sys.addOrder(new Order("O001", "Charlie", 500.0)); // 測試重複編號

        System.out.println("\n=== 3. 顯示下一筆待處理訂單 ===");
        System.out.println("下一筆待處理: " + sys.peekNextPendingOrder());

        System.out.println("\n=== 4. 依金額降冪排序 (Merge Sort) ===");
        Order[] orderArray = sys.getAllOrders().toArray(new Order[0]);
        OrderAlgorithms.sortByAmountDescending(orderArray, 0, orderArray.length - 1);
        System.out.println("降冪排序結果:");
        for (Order o : orderArray) {
            System.out.println("  " + o);
        }

        System.out.println("\n=== 5. 依顧客姓名搜尋全部訂單 (含找不到資料) ===");
        System.out.println("搜尋 Alice 的訂單: " + OrderAlgorithms.searchByCustomerName(orderArray, "Alice"));
        System.out.println("搜尋 David 的訂單: " + OrderAlgorithms.searchByCustomerName(orderArray, "David"));

        System.out.println("\n=== 6. 模擬訂單處理流程過程，檢查狀態一致性 ===");
        sys.processNextOrder();
        System.out.println("處理完一筆後，下一筆待處理: " + sys.peekNextPendingOrder());
    }
}
