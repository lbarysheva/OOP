package nsu.barysheva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeapSortTest {
    @Test
    void test_swap() {
        int[] arr = {1, 2, 3};
        int[] must_be = {3, 2, 1};
        HeapSort ob = new HeapSort();
        ob.swap(arr, 2, 0);
        Assertions.assertArrayEquals(must_be, arr);
    }

    @Test
    void test_heapify() {
        int[] arr1 = {1, 2, 3};
        int[] must_be1 = {3, 2, 1};
        HeapSort ob = new HeapSort();
        ob.heapify(arr1, 3, 0);
        Assertions.assertArrayEquals(must_be1, arr1);
        int[] arr2 = {1, 3, 2, 4};
        int[] must_be2 = {3, 4, 2, 1};
        ob.heapify(arr2, 4, 0);
        Assertions.assertArrayEquals(must_be2, arr2);
    }

    @Test
    void test_sort() {
        int[] a = {1, 2, 3, 4, 5};
        int[] testArray = {2, 3, 1, 4, 5};
        HeapSort ob = new HeapSort();
        ob.sort(testArray, 5);
        Assertions.assertArrayEquals(a, testArray);
    }

}