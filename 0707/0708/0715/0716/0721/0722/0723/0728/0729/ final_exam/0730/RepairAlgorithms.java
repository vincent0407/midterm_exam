import java.util.ArrayList;
import java.util.List;

public class RepairAlgorithms {

    public static void sortByPriorityDescending(List<RepairTask> tasks, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortByPriorityDescending(tasks, left, mid);
        sortByPriorityDescending(tasks, mid + 1, right);
        merge(tasks, left, mid, right);
    }

    private static void merge(List<RepairTask> tasks, int left, int mid, int right) {
        List<RepairTask> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (tasks.get(i).getPriority() >= tasks.get(j).getPriority()) {
                temp.add(tasks.get(i++));
            } else {
                temp.add(tasks.get(j++));
            }
        }

        while (i <= mid) temp.add(tasks.get(i++));
        while (j <= right) temp.add(tasks.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            tasks.set(left + k, temp.get(k));
        }
    }

    public static RepairTask searchById(List<RepairTask> tasks, String id) {
        for (RepairTask t : tasks) {
            if (t.getTaskId().equalsIgnoreCase(id)) return t;
        }
        return null;
    }

    public static List<RepairTask> searchByDeviceName(List<RepairTask> tasks, String name) {
        List<RepairTask> result = new ArrayList<>();
        for (RepairTask t : tasks) {
            if (t.getDeviceName().equalsIgnoreCase(name)) result.add(t);
        }
        return result;
    }
}
