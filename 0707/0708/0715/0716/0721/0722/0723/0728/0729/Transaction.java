public class Transaction {
    private String txnId;    
    private String account;  
    private double amount;    
    private long timestamp;   

    public Transaction(String txnId, String account, double amount, long timestamp) {
        this.txnId = txnId;
        this.account = account;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount() { return amount; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("交易號: %-6s | 帳號: %-8s | 金額: %8.2f | 時間戳: %d", txnId, account, amount, timestamp);
    }
}
