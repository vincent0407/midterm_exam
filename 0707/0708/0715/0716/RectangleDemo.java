public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(5.0, 5.0);  
        Rectangle r2 = new Rectangle(4.0, 6.5);   
        Rectangle r3 = new Rectangle(10.0, 20.0); 

        System.out.println("--- 測試不合法欄位驗證 ---");
        Rectangle r4 = new Rectangle(-3, 5); 
        System.out.println(r4);
        System.out.println("-------------------------\n");

        displayRectangleInfo(r1);
        displayRectangleInfo(r2);
        displayRectangleInfo(r3);
    }

    public static void displayRectangleInfo(Rectangle r) {
        System.out.println(r.toString());
        System.out.println("面積: " + r.calculateArea());
        System.out.println("周長: " + r.calculatePerimeter());
        System.out.println("是否為正方形? " + (r.isSquare() ? "是" : "否"));
        System.out.println("-------------------------");
    }
}
