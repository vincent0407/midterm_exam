# 系統測試案例紀錄表 (TestCases.md)

以下為系統核心功能之測試案例紀錄：

| 案例編號 | 測試項目 | 輸入資料 | 操作步驟 | 預期結果 | 實際結果 | 狀態 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **TC-01** | 空資料搜尋 | `list = []`, Target=`B001` | 執行 `binarySearchById` | 回傳 `null` | 回傳 `null` | **通過** |
| **TC-02** | 單筆資料排序 | `list = [B001]` | 執行 `sortById` | 清單維持 `[B001]` | 清單維持 `[B001]` | **通過** |
| **TC-03** | 防止重複編號 | 重複 ID `B001` | 執行 `addBook()` | 顯示錯誤提示並回傳 `false` | 正確擋下重複並顯示錯誤 | **通過** |
| **TC-04** | 二分搜尋-第一筆 | Target = 清單首項 ID | 執行 `binarySearchById` | 正確回傳首項物件 | 正確回傳首項物件 | **通過** |
| **TC-05** | 二分搜尋-最後一筆 | Target = 清單尾項 ID | 執行 `binarySearchById` | 正確回傳尾項物件 | 正確回傳尾項物件 | **通過** |
| **TC-06** | 搜尋不存在資料 | Target = `X999` | 執行 `binarySearchById` | 回傳 `null` | 回傳 `null` | **通過** |
| **TC-07** | 空 Queue 取出操作 | `waitlistQueue = []` | 執行 `poll()` / `completeTask()` | 回傳 `null` 且不崩潰 | 回傳 `null` 且系統穩定 | **通過** |
| **TC-08** | 空 Stack 復原操作 | `cancelStack = []` | 執行 `pop()` / `undoCompletion()` | 回傳 `null` 且不崩潰 | 回傳 `null` 且系統穩定 | **通過** |
| **TC-09** | 報名額滿自動進候補 | 容量上限 2，新增第 3 筆 | 執行 `register()` | 第 3 筆進入 Queue 候補區 | 第 3 筆正確排入 Queue | **通過** |
| **TC-10** | 取消報名與自動遞補 | 正取取消 1 筆 | 執行 `cancelRegistration()` | 被取消者進 Stack，Queue 首位遞補正取 | Stack 增加紀錄，Queue 成功遞補 | **通過** |
| **TC-11** | 依等級穩定排序 | 同優先度之兩筆任務 | 執行 `sortByPriorityDescending` | 排序後保持原登記順序 | 順序保持一致 (穩定排序) | **通過** |
| **TC-12** | 順序搜尋查無結果 | Category = `UNKNOWN` | 執行 `sequentialSearchByCategory` | 回傳空 List `[]` | 回傳空 List `[]` | **通過** |
| **TC-13** | 嘗試取消不存在紀錄 | Target ID = `R999` | 執行 `cancelRegistration()` | 顯示錯誤訊息，狀態維持不變 | 顯示失敗提示，無異常變更 | **通過** |
| **TC-14** | 多筆金額降冪排序 | 金額 `[1500, 3200, 800]` | 執行 Merge Sort | 結果為 `[3200, 1500, 800]` | 結果為 `[3200, 1500, 800]` | **通過** |
| **TC-15** | 演算法比較次數算定 | 1024 筆固定亂數 | 執行 `AlgorithmComparisonReport` | Merge Sort 比較次數顯著少於 Insertion/Selection | Merge Sort 表現最佳，結果符合理論 | **通過** |

---

## 測試失敗與修正記錄（修復範例）

* **測試項目**：TC-10 取消報名與自動遞補
* **初始問題**：取消正取時，候補遞補後，`mainRegistrations` 數量超額。
* **修正內容**：在 `EventRegistrationSystem.java` 的 `cancelRegistration` 方法中，確保先將取消項目自 `mainRegistrations` 移除後，才執行 `waitlistQueue.poll()` 遞補。
* **重新測試結果**：**通過**。
