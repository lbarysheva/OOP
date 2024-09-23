package nsu.barysheva;

/**
 * HeapSort class, which implements pyramidal sorting (heap sorting).
 */

public class HeapSort {

    /**
     * Swaps two elements in an array indexes a and b.
     * @param arr - input array
     * @param a - index of first element
     * @param b - index of second element
     */

    void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * From the array arr of length n for the element with index i creates a heap of the type:
     * both children are smaller than the parent. Using recursion.
     * @param arr - input array
     * @param n - length of the array
     * @param i - needed index of element
     */

    void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    /**
     * Heap sorting for all elements, change the order to the correct order (ascending)
     * @param arr - input array
     * @param n - length of the array
     */

    void sort(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    /**
     * Method main with test array.
     *
     * @param args - input array
     */

    public static void main(String[] args) {
    }
}