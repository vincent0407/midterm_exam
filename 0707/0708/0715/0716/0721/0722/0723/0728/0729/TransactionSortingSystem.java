public class TransactionSortingSystem {

    public static void sortTransactions(Transaction[] list) {
        for (int i = 1; i < list.length; i++) {
            Transaction key = list[i];
            int j = i - 1;

            while (j >= 0 && shouldSwap(list[j], key)) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key;
        }
    }

    private static boolean shouldSwap(Transaction current, Transaction key) {
        if (current.getAmount() < key.getAmount()) {
            return true; // 金額較小者往後排 (金額降冪)
        } else if (current.getAmount() == key.getAmount()) {
            return current.getTimestamp() > key.getTimestamp(); // 金額相同，時間戳較大者往後排 (時間升冪)
        }
        return false;
    }

    public static void main(String[] args) {
        Transaction[] txns = {
            new Transaction("TXN001", "ACC-A", 1500.0, 1003L),
            new Transaction("TXN002", "ACC-B", 3000.0, 1001L),
            new Transaction("TXN003", "ACC-C", 1500.0, 1001L), 
            new Transaction("TXN004", "ACC-D", 5000.0, 1004L),
            new Transaction("TXN005", "ACC-E", 1500.0, 1002L)  
        };

        System.out.println("=== 排序前交易紀錄 ===");
        for (Transaction t : txns) {
            System.out.println(t);
        }

        sortTransactions(txns);

        System.out.println("\n=== 排序後交易紀錄 (金額降冪 -> 時間升冪) ===");
        for (Transaction t : txns) {
            System.out.println(t);
        }
    }
}
