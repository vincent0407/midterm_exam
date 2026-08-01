import java.util.ArrayList;
import java.util.List;

public class RegistrationAlgorithms {

    // Merge Sort：依報名編號排序
    public static void sortById(List<Registration> list, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortById(list, left, mid);
        sortById(list, mid + 1, right);
        merge(list, left, mid, right);
    }

    private static void merge(List<Registration> list, int left, int mid, int right) {
        List<Registration> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (list.get(i).getRegId().compareTo(list.get(j).getRegId()) <= 0) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }
        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));
        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    public static Registration binarySearchById(List<Registration> sortedList, String targetId) {
        int left = 0, right = sortedList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = sortedList.get(mid).getRegId().compareTo(targetId);
            if (cmp == 0) return sortedList.get(mid);
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    public static List<Registration> sequentialSearchByName(List<Registration> list, String name) {
        List<Registration> result = new ArrayList<>();
        for (Registration r : list) {
            if (r.getName().equalsIgnoreCase(name)) {
                result.add(r);
            }
        }
        return result;
    }
}
