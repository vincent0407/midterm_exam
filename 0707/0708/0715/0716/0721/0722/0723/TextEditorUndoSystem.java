import java.util.Stack;

public class TextEditorUndoSystem {

    private StringBuilder currentText;
    private Stack<String> historyStack;

    public TextEditorUndoSystem() {
        this.currentText = new StringBuilder();
        this.historyStack = new Stack<>();
    }

    private void saveState() {
        historyStack.push(currentText.toString());
    }

    public void addText(String text) {
        saveState(); 
        currentText.append(text);
        System.out.println("【新增文字】: \"" + text + "\" | 目前內容: \"" + currentText + "\"");
    }

    public void deleteLast(int count) {
        if (count <= 0) return;

        saveState(); 

        int length = currentText.length();
        if (count >= length) {
            currentText.setLength(0); 
        } else {
            currentText.delete(length - count, length);
        }
        System.out.println("【刪除最後 " + count + " 個字】 | 目前內容: \"" + currentText + "\"");
    }

    public void undo() {
        if (historyStack.isEmpty()) {
            System.out.println("【警告】沒有歷史紀錄可以 Undo！");
            return;
        }
        String previousState = historyStack.pop();
        currentText = new StringBuilder(previousState);
        System.out.println("【執行 Undo】復原成功 | 目前內容: \"" + currentText + "\"");
    }

    public void displayText() {
        System.out.println(">>> 當前編輯器內容: \"" + currentText + "\"");
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 開始測試文字編輯器 Undo 功能 ===");

        editor.undo();

        System.out.println("\n--- 開始編輯 ---");
        editor.addText("Hello");
        editor.addText(" World");
        editor.addText("!");
        editor.displayText();

        System.out.println("\n--- 執行刪除 ---");
        editor.deleteLast(3); // 刪除 "ld!"
        editor.displayText();

        editor.addText(" Java Code");
        editor.displayText();

        System.out.println("\n--- 開始連續 Undo 測試 (至少 3 次) ---");
        editor.undo();

        editor.undo();

        editor.undo();

        editor.undo();

        System.out.println("\n--- 驗證最終結果 ---");
        editor.displayText();
    }
}
