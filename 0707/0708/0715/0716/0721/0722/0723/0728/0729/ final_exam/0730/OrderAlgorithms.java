import java.util.ArrayList;
import java.util.List;

public class OrderAlgorithms {

    public static void sortByAmountDescending(Order[] orders, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortByAmountDescending(orders, left, mid);
        sortByAmountDescending(orders, mid + 1, right);
        mergeDescending(orders, left, mid, right);
    }

    private static void mergeDescending(Order[] orders, int left, int mid, int right) {
        Order[] temp = new Order[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // 降冪條件：比較金額大的優先放前面
            if (orders[i].getAmount() >= orders[j].getAmount()) {
                temp[k++] = orders[i++];
            } else {
                temp[k++] = orders[j++];
            }
        }

        while (i <= mid) temp[k++] = orders[i++];
        while (j <= right) temp[k++] = orders[j++];

        System.arraycopy(temp, 0, orders, left, temp.length);
    }

    public static List<Order> searchByCustomerName(Order[] orders, String name) {
        List<Order> result = new ArrayList<>();
        if (orders == null) return result;
        for (Order order : orders) {
            if (order.getCustomerName().equalsIgnoreCase(name)) {
                result.add(order);
            }
        }
        return result;
    }
}
