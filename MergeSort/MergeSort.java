
public class MergeSort {
    void merge(int arr[], int left, int mid, int right) {
        int l = mid - left + 1;
        int r = right - mid;

        int leftArray[] = new int[l]; // New array for Left elements
        int rightArray[] = new int[r]; // New array for Right elements

        // Copy elements into leftArray
        for (int i = 0; i < l; ++i) {
            leftArray[i] = arr[left + i];
        }

        // Copy elements into rightArray
        for (int j = 0; j < r; ++j) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        
        // Merge the two arrays
        while (i < l && j < r) {
            if (leftArray[i] >= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from leftArray (if any)
        while (i < l) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements from rightArray (if any)
        while (j < r) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    void sort(int arr[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(arr, left, mid);  // Sort left half
            sort(arr, mid + 1, right);  // Sort right half
            merge(arr, left, mid, right);  // Merge sorted halves
        }
    }

    public static void main(String args[]) {
        int arr[] = {90, 23, 101, 45, 65, 23, 67, 89, 34, 23};
        MergeSort ob = new MergeSort();  // Creating object of MergeSort
        ob.sort(arr, 0, arr.length - 1);  // Calling the method

        // Printing the output
        System.out.print("Sorted array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
