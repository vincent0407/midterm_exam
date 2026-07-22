import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private static final ArrayList<Course> courseList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-7): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addCourse();
                case "2" -> enrollCourse();
                case "3" -> dropCourse();
                case "4" -> deleteCourse();
                case "5" -> searchCourse();
                case "6" -> displaySystemSummary();
                case "7" -> {
                    System.out.println("系統已結束，謝謝使用！");
                    running = false;
                }
                default -> System.out.println("❌ 錯誤：請輸入有效的選項 (1-7)！");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("==================================");
        System.out.println("          選課管理系統            ");
        System.out.println("==================================");
        System.out.println("1. 新增課程");
        System.out.println("2. 學生選課 (加選)");
        System.out.println("3. 學生退選");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程");
        System.out.println("6. 顯示系統統計資訊");
        System.out.println("7. 離開系統");
        System.out.println("==================================");
    }

    private static void addCourse() {
        System.out.print("請輸入課程代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("❌ 錯誤：課程代碼不可為空白！");
            return;
        }

        if (findCourseById(id) != null) {
            System.out.println("❌ 錯誤：課程代碼 \"" + id + "\" 已存在！");
            return;
        }

        System.out.print("請輸入課程名稱: ");
        String name = scanner.nextLine().trim();

        System.out.print("請輸入課程容量 (上限人數): ");
        int capacity = readIntInput();
        if (capacity <= 0) {
            System.out.println("❌ 錯誤：課程容量必須大於 0！");
            return;
        }

        courseList.add(new Course(id, name, capacity));
        System.out.println("✅ 成功新增課程: " + name);
    }

    private static void enrollCourse() {
        System.out.print("請輸入要加選的課程代碼: ");
        String id = scanner.nextLine().trim();

        Course course = findCourseById(id);
        if (course == null) {
            System.out.println("❌ 加選失敗：找不到代碼為 \"" + id + "\" 的課程。");
            return;
        }

        if (course.isFull()) {
            System.out.println("❌ 加選失敗：課程 \"" + course.getName() + "\" 已額滿！");
        } else {
            course.enroll();
            System.out.println("✅ 加選成功！目前人數: " + course.getCurrentEnrolled() + "/" + course.getCapacity());
        }
    }

    private static void dropCourse() {
        System.out.print("請輸入要退選的課程代碼: ");
        String id = scanner.nextLine().trim();

        Course course = findCourseById(id);
        if (course == null) {
            System.out.println("❌ 退選失敗：找不到代碼為 \"" + id + "\" 的課程。");
            return;
        }

        if (course.drop()) {
            System.out.println("✅ 退選成功！目前人數: " + course.getCurrentEnrolled() + "/" + course.getCapacity());
        } else {
            System.out.println("⚠️ 退選失敗：該課程目前選課人數已為 0。");
        }
    }

    private static void deleteCourse() {
        System.out.print("請輸入要刪除的課程代碼: ");
        String id = scanner.nextLine().trim();

        Course course = findCourseById(id);
        if (course != null) {
            courseList.remove(course);
            System.out.println("✅ 成功刪除課程: " + course.getName());
        } else {
            System.out.println("❌ 刪除失敗：找不到代碼為 \"" + id + "\" 的課程。");
        }
    }

    private static void searchCourse() {
        System.out.print("請輸入要搜尋的課程代碼或名稱: ");
        String keyword = scanner.nextLine().trim();

        boolean found = false;
        for (Course c : courseList) {
            if (c.getId().equalsIgnoreCase(keyword) || c.getName().contains(keyword)) {
                System.out.println("🔍 搜尋結果: " + c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("⚠️ 找不到符合條件的課程。");
        }
    }

    private static void displaySystemSummary() {
        int totalCourses = courseList.size();
        int totalEnrolledCount = 0;
        int fullCourseCount = 0;

        System.out.println("📋 所有課程清單與狀態：");
        for (Course c : courseList) {
            System.out.println(c);
            totalEnrolledCount += c.getCurrentEnrolled();
            if (c.isFull()) {
                fullCourseCount++;
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("📊 系統統計結果：");
        System.out.println("總課程數: " + totalCourses + " 門");
        System.out.println("總選課人次: " + totalEnrolledCount + " 人次");
        System.out.println("額滿課程數: " + fullCourseCount + " 門");
    }

    private static Course findCourseById(String id) {
        for (Course c : courseList) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    private static int readIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
