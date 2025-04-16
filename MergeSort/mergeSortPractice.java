public class mergeSortPractice {

    void mergeSort(int arr[], int left, int mid, int right) {
        int l = mid - left + 1;
        int r = right - mid;

        int leftArray[] = new int[l];
        int rightArray[] = new int[r];

        for (int i = 0; i < l; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int i = 0; i < r; i++) {
            rightArray[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < l && j < r) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;  
            }
            k++;
        }

        while (i < l) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < r) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    void sort(int arr[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            mergeSort(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        int arr[] = {20, 38, 90, 32, 98, 85, 39, 2, 23, 24, 21, 4};

        mergeSortPractice ob = new mergeSortPractice();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
