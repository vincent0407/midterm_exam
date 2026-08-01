import java.util.ArrayList;
import java.util.List;

public class BookAlgorithms {

    public static void sortByIdAscending(List<Book> books, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortByIdAscending(books, left, mid);
        sortByIdAscending(books, mid + 1, right);
        mergeById(books, left, mid, right);
    }

    private static void mergeById(List<Book> books, int left, int mid, int right) {
        List<Book> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (books.get(i).getBookId().compareTo(books.get(j).getBookId()) <= 0) {
                temp.add(books.get(i++));
            } else {
                temp.add(books.get(j++));
            }
        }
        while (i <= mid) temp.add(books.get(i++));
        while (j <= right) temp.add(books.get(j++));
        for (int k = 0; k < temp.size(); k++) {
            books.set(left + k, temp.get(k));
        }
    }

    public static void sortByBorrowCountDescending(List<Book> books, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortByBorrowCountDescending(books, left, mid);
        sortByBorrowCountDescending(books, mid + 1, right);
        mergeByBorrowCount(books, left, mid, right);
    }

    private static void mergeByBorrowCount(List<Book> books, int left, int mid, int right) {
        List<Book> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (books.get(i).getBorrowCount() >= books.get(j).getBorrowCount()) {
                temp.add(books.get(i++));
            } else {
                temp.add(books.get(j++));
            }
        }
        while (i <= mid) temp.add(books.get(i++));
        while (j <= right) temp.add(books.get(j++));
        for (int k = 0; k < temp.size(); k++) {
            books.set(left + k, temp.get(k));
        }
    }

    public static Book binarySearchById(List<Book> sortedBooks, String targetId) {
        int left = 0, right = sortedBooks.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = sortedBooks.get(mid).getBookId().compareTo(targetId);
            if (cmp == 0) return sortedBooks.get(mid);
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    public static List<Book> sequentialSearchByCategory(List<Book> books, String category) {
        List<Book> result = new ArrayList<>();
        if (books == null) return result;
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }
}
