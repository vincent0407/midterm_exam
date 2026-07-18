public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount("A01", "Alice", 1000);
        BankAccount accountB = new BankAccount("B02", "Bob", 500);

        System.out.println("--- 初始狀態 ---");
        System.out.println(accountA);
        System.out.println(accountB);
        System.out.println();

        System.out.println("--- 測試存款 ---");
        System.out.println("Alice 存款 500 元: " + accountA.deposit(500));
        System.out.println("Alice 存款 -100 元 (不合法): " + accountA.deposit(-100));
        System.out.println(accountA);
        System.out.println();

        System.out.println("--- 測試提款 ---");
        System.out.println("Bob 提款 200 元: " + accountB.withdraw(200));
        System.out.println("Bob 提款 2000 元 (超支): " + accountB.withdraw(2000));
        System.out.println(accountB);
        System.out.println();

        System.out.println("--- 測試成功轉帳 ---");
        System.out.println("Alice 轉帳 400 元給 Bob: " + accountA.transferTo(accountB, 400));
        System.out.println(accountA);
        System.out.println(accountB);
        System.out.println();

        System.out.println("--- 測試失敗轉帳 (餘額不足) ---");
        System.out.println("Bob 嘗試轉帳 5000 元給 Alice: " + accountB.transferTo(accountA, 5000));
        System.out.println(accountA);
        System.out.println(accountB);
    }
}
