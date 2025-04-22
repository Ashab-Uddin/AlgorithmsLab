import java.util.ArrayList;
import java.util.List;

public class MergeEven {

    // Function to merge two sorted subarrays
    void merge(int[] arr, int start, int mid, int end) {
        List<Integer> temp = new ArrayList<>();
        int i = start;
        int j = mid + 1;

        // Compare and merge elements from both halves
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                if(arr[i]%2 == 0){
                    temp.add(arr[i]);
                i++;
                }
                
            } else {
                if(arr[i]%2 ==0){
                    temp.add(arr[j]);
                j++;
                }
                
            }
        }

        // Copy remaining elements from left half (if any)
        while (i <= mid) {
            temp.add(arr[i]);
            i++;
        }

        // Copy remaining elements from right half (if any)
        while (j <= end) {
            temp.add(arr[j]);
            j++;
        }

        // Copy the merged result back to the original array
        for (int k = 0; k < temp.size(); k++) {
            arr[start + k] = temp.get(k);
        }
    }

    // Recursive function to perform merge sort
    void sort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            sort(arr, start, mid);       // Sort left half
            sort(arr, mid + 1, end);     // Sort right half
            merge(arr, start, mid, end); // Merge both halves
        }
    }

    // Main method to test the sorting
    public static void main(String[] args) {
        int[] arr = {90, 23, 101, 45, 65, 23, 67, 89, 34, 23};

        MergeEven sorter = new MergeEven();
        sorter.sort(arr, 0, arr.length - 1);

        // Print the sorted array
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
