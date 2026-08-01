import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    private List<Book> bookList = new ArrayList<>();

    public boolean addBook(Book book) {
        if (book == null) return false;
        for (Book b : bookList) {
            if (b.getBookId().equals(book.getBookId())) {
                System.out.println("[錯誤] 重複的書籍編號: " + book.getBookId());
                return false;
            }
        }
        bookList.add(book);
        return true;
    }

    public List<Book> getBookList() { return bookList; }

    public static void main(String[] args) {
        LibraryManagementSystem sys = new LibraryManagementSystem();

        System.out.println("=== 測試 1：空資料處理 ===");
        System.out.println("二分搜尋空清單: " + BookAlgorithms.binarySearchById(sys.getBookList(), "B001"));
        System.out.println("順序搜尋空清單: " + BookAlgorithms.sequentialSearchByCategory(sys.getBookList(), "CS"));

        System.out.println("\n=== 測試 2：新增書籍與重複編號阻擋 ===");
        sys.addBook(new Book("B003", "Java Programming", "CS", 150));
        sys.addBook(new Book("B001", "Data Structures", "CS", 300));
        sys.addBook(new Book("B002", "Economics 101", "ECO", 80));
        sys.addBook(new Book("B001", "Duplicate Book", "CS", 10)); // 重複測試

        System.out.println("\n=== 測試 3：Merge Sort 依書號升冪排序 ===");
        BookAlgorithms.sortByIdAscending(sys.getBookList(), 0, sys.getBookList().size() - 1);
        sys.getBookList().forEach(System.out::println);

        System.out.println("\n=== 測試 4：Binary Search 依書號查詢 ===");
        System.out.println("搜尋 B002: " + BookAlgorithms.binarySearchById(sys.getBookList(), "B002"));
        System.out.println("搜尋 B099 (找不到): " + BookAlgorithms.binarySearchById(sys.getBookList(), "B099"));

        System.out.println("\n=== 測試 5：Sequential Search 依分類查詢 ===");
        System.out.println("分類 CS: " + BookAlgorithms.sequentialSearchByCategory(sys.getBookList(), "CS"));
        System.out.println("分類 MATH (找不到): " + BookAlgorithms.sequentialSearchByCategory(sys.getBookList(), "MATH"));

        System.out.println("\n=== 測試 6：Merge Sort 依借閱次數降冪排序 ===");
        BookAlgorithms.sortByBorrowCountDescending(sys.getBookList(), 0, sys.getBookList().size() - 1);
        sys.getBookList().forEach(System.out::println);
    }
}
