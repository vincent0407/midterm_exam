import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class CounterServiceSystem {

    static class Customer {
        private int number;
        private String name;

        public Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "號碼 [" + number + "] " + name;
        }
    }

    private Queue<Customer> queue;
    private List<Customer> history;
    private int ticketCounter;

    public CounterServiceSystem() {
        this.queue = new LinkedList<>();
        this.history = new ArrayList<>();
        this.ticketCounter = 1;
    }

    public void takeTicket(String name) {
        Customer customer = new Customer(ticketCounter++, name);
        queue.offer(customer);
        System.out.println("【取號成功】" + customer + " 已進入等待佇列。");
    }

    public void callNext() {
        if (queue.isEmpty()) {
            System.out.println("【提示】目前沒有等待中的顧客，無法叫號！");
            return;
        }
        Customer servedCustomer = queue.poll();
        history.add(servedCustomer);
        System.out.println("【櫃台叫號】請 " + servedCustomer + " 至櫃台辦理！");
    }

    public void showStatus() {
        System.out.println("----------------------------------------");
        System.out.println("當前等待人數: " + queue.size() + " 人");
        if (queue.isEmpty()) {
            System.out.println("下一位顧客: 無");
        } else {
            System.out.println("下一位顧客: " + queue.peek());
        }
        System.out.println("----------------------------------------");
    }

    public void showHistory() {
        System.out.println("\n=== 已服務顧客紀錄 ===");
        if (history.isEmpty()) {
            System.out.println("尚無已服務的紀錄。");
        } else {
            for (Customer c : history) {
                System.out.println(" - " + c);
            }
        }
    }

    public static void main(String[] args) {
        CounterServiceSystem system = new CounterServiceSystem();

        System.out.println("=== 櫃台叫號系統測試 ===\n");

        system.callNext();

        system.takeTicket("張小明");
        system.takeTicket("陳美麗");
        system.takeTicket("林大熊");

        system.showStatus();

        system.callNext();
        system.callNext();

        system.showStatus();

        system.takeTicket("黃阿瑪");

        system.callNext();
        system.callNext();

        system.callNext();

        system.showHistory();
    }
}
