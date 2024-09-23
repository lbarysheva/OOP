package nsu.barysheva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeapSortTest {
    @Test
    void test_swap() {
        int[] arr = {1, 2, 3};
        int[] must = {3, 2, 1};
        HeapSort ob = new HeapSort();
        ob.swap(arr, 2, 0);
        Assertions.assertArrayEquals(must, arr);
    }

    @Test
    void test_heapify() {
        int[] arr1 = {1, 2, 3};
        int[] must1 = {3, 2, 1};
        HeapSort ob = new HeapSort();
        ob.heapify(arr1, 3, 0);
        Assertions.assertArrayEquals(must1, arr1);
        int[] arr2 = {1, 3, 2, 4};
        int[] must2 = {3, 4, 2, 1};
        ob.heapify(arr2, 4, 0);
        Assertions.assertArrayEquals(must2, arr2);
    }


}