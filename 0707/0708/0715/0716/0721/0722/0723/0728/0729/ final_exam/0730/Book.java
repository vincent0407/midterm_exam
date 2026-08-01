public class Book {
    private String bookId;     
    private String title;       
    private String category;   
    private int borrowCount;  

    public Book(String bookId, String title, String category, int borrowCount) {
        this.bookId = bookId;
        this.title = title;
        this.category = category;
        this.borrowCount = borrowCount;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public int getBorrowCount() { return borrowCount; }

    @Override
    public String toString() {
        return String.format("Book[ID=%s, Title=%s, Category=%s, BorrowCount=%d]",
                bookId, title, category, borrowCount);
    }
}
