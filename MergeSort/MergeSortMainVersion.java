import java.util.ArrayList;
import java.util.List;

public class MergeSortMainVersion {

    // merge function
    void merge(int[] arr, int st, int mid, int end) {
        List<Integer> temp = new ArrayList<>();
        int i = st, j = mid + 1;

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp.add(arr[i]);
                i++;
            } else {
                temp.add(arr[j]);
                j++;
            }
        }

        while (i <= mid) {
            temp.add(arr[i]);
            i++;
        }

        while (j <= end) {
            temp.add(arr[j]);
            j++;
        }

        for (int idx = 0; idx < temp.size(); idx++) {
            arr[st + idx] = temp.get(idx);
        }
    }

    // merge sort recursive function
    void sort(int[] arr, int st, int end) {
        if (st < end) {
            int mid = (st + end) / 2;
            sort(arr, st, mid);
            sort(arr, mid + 1, end);
            merge(arr, st, mid, end);
        }
    }

    // main method
    public static void main(String[] args) {
        int[] arr = {90, 23, 101, 45, 65, 23, 67, 89, 34, 23};

        MergeSortMainVersion ob = new MergeSortMainVersion();
        ob.sort(arr, 0, arr.length - 1);

        // Print the sorted array
        System.out.println("Sorted array:");
        for (int val : arr) {
            System.out.print(val + " ");
        }
    }
}
