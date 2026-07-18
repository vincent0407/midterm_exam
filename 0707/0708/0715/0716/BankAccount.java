public class BankAccount {
    private String accountNumber;
    private String name;
    private int balance;

    public BankAccount(String accountNumber, String name, int initialBalance) {
        this.accountNumber = accountNumber;
        this.name = name;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("警告：初始餘額不能為負數，已設定為 0。");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public boolean deposit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        System.out.println("存款失敗：金額必須大於 0。");
        return false;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("提款失敗：金額必須大於 0。");
            return false;
        }
        if (amount > this.balance) {
            System.out.println("提款失敗：餘額不足。");
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public boolean transferTo(BankAccount target, int amount) {
        if (amount <= 0) {
            System.out.println("轉帳失敗：金額必須大於 0。");
            return false;
        }
        if (amount > this.balance) {
            System.out.println("轉帳失敗：餘額不足。");
            return false;
        }
        if (target == null) {
            System.out.println("轉帳失敗：無效的目標帳戶。");
            return false;
        }

        this.balance -= amount;
        target.deposit(amount); 
        return true;
    }

    @Override
    public String toString() {
        return "帳戶[" + accountNumber + "] 戶名: " + name + ", 餘額: " + balance + " 元";
    }
}
