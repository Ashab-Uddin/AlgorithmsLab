class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MergeList {

    // Helper: Convert array to linked list
    Node buildList(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    // Merge two sorted linked lists
    Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node result;
        if (left.data <= right.data) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }

    // Find the middle of the linked list
    Node getMiddle(Node head) {
        if (head == null || head.next == null) return head;

        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Merge sort function
    Node mergeSort(Node head) {
        if (head == null || head.next == null) return head;

        Node mid = getMiddle(head);
        Node rightHead = mid.next;
        mid.next = null;

        Node leftSorted = mergeSort(head);
        Node rightSorted = mergeSort(rightHead);

        return merge(leftSorted, rightSorted);
    }

    // Print the linked list
    void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
        MergeList list = new MergeList();

        int[] inputArray = {90, 23, 101, 45, 65, 23, 67, 89, 34, 23};
        Node head = list.buildList(inputArray);

        System.out.println("Original List:");
        list.printList(head);

        head = list.mergeSort(head);

        System.out.println("Sorted List:");
        list.printList(head);
    }
}
