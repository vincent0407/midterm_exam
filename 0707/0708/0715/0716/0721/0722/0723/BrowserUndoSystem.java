import java.util.Stack;

public class BrowserUndoSystem {

    private Stack<String> history;

    public BrowserUndoSystem() {

        this.history = new Stack<>();
    }

    public void openPage(String url) {

        history.push(url);
        System.out.println("開啟頁面: " + url);
    }

    public void back() {

        if (history.isEmpty()) {
            System.out.println("【警告】沒有上一頁可以返回！");
            return;
        }
        String removed = history.pop();
        System.out.println("離開頁面: " + removed);
        
        if (!history.isEmpty()) {
            System.out.println("當前頁面: " + history.peek());
        } else {
            System.out.println("當前頁面: [空白頁]");
        }
    }

    public void currentPage() {

        if (history.isEmpty()) {
            System.out.println("當前頁面: [空白頁]");
        } else {
            System.out.println("當前頁面: " + history.peek());
        }
    }

    public static void main(String[] args) {

        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 開始進行 8 次以上操作測試 ===");

        browser.currentPage();

        browser.back();

        browser.openPage("https://www.google.com");

        browser.openPage("https://github.com");

        browser.currentPage();

        browser.openPage("https://www.youtube.com");

        browser.back();

        browser.back();

        browser.back();

        browser.back();
    }
}
