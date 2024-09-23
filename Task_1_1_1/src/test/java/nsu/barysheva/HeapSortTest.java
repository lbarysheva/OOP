package nsu.barysheva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapSortTest {

    @Test
    void test_sort1() {
        int[] a = {1, 2, 3, 4, 5};
        int[] testArray = {2, 3, 1, 4, 5};
        HeapSort ob = new HeapSort();
        HeapSort.sort(testArray, 5);
        assertArrayEquals(a, testArray);
    }

    @Test
    void test_sort2() {
        int[] a = {1, 1, 1, 1, 1};
        int[] testArray = {1, 1, 1, 1, 1};
        HeapSort ob = new HeapSort();
        HeapSort.sort(testArray, 5);
        assertArrayEquals(a, testArray);
    }

    @Test
    void test_sort3() {
        int[] a = {-99991, -6781, 81, 81, 100000000};
        int[] testArray = {100000000, -6781, -99991, 81, 81};
        HeapSort ob = new HeapSort();
        HeapSort.sort(testArray, 5);
        assertArrayEquals(a, testArray);
    }

    @Test
    void test_sort4() {
        int[] a = {1, 2, 3, 3, 3};
        int[] testArray = {3, 2, 3, 1, 3};
        HeapSort ob = new HeapSort();
        HeapSort.sort(testArray, 5);
        assertArrayEquals(a, testArray);
    }

    @Test
    void main_test(){
        HeapSort.main(null);
        assertTrue(true);
    }

}