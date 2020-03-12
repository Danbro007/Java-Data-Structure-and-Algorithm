package algorithm;

import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        int[] array = {53, 3, 542, 748, 14, 214};
        int[] temp = new int[array.length];
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }


    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int temp;
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }

        }
    }


    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int index = i - 1;
            int cur = array[i];
            while (index >= 0 && cur < array[index]) {
                array[index + 1] = array[index];
                index--;
            }

            array[index + 1] = cur;

        }

    }


    public static void shellSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int index = i - gap;
                int cur = array[i];
                while (index >= 0 && cur < array[index]) {
                    array[index + gap] = array[index];
                    index -= gap;
                }
                array[index + gap] = cur;

            }

        }

    }


    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = min;
                array[minIndex] = temp;
            }


        }

    }

    public static void quickSort(int[] array, int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int midValue = array[low];
        while (i < j) {
            while (i < j && array[j] >= midValue) {
                j--;
            }
            while (i < j && array[i] <= midValue) {
                i++;
            }

            if (i < j) {
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        array[low] = array[i];
        array[i] = midValue;
        quickSort(array, low, i - 1);
        quickSort(array, i + 1, high);
    }


    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }

    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (array[i] < array[j]) {
                temp[index++] = array[i++];
            } else {
                temp[index++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = array[i++];
        }
        while (j <= right) {
            temp[index++] = array[j++];
        }

        index = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            array[tempLeft++] = temp[index++];

        }
    }


    public static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            adjustHeap(array, 0, i);

        }
    }


    public static void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            if (temp < array[k]) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    public static void radixSort(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        int maxLength = String.valueOf(max).length();
        int[][] buckets = new int[10][array.length];
        int[] bucketElementNum = new int[10];
        int index = 0;
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < array.length; j++) {
                int n = (int) Math.pow(10, i);
                int m = array[j] / n % 10;
                buckets[m][bucketElementNum[m]] = array[j];
                bucketElementNum[m]++;
            }
            for (int j = 0; j < buckets.length; j++) {
                if (bucketElementNum[j] > 0) {
                    for (int k = 0; k < bucketElementNum[j]; k++) {
                        array[index] = buckets[j][k];
                        index++;
                    }
                    bucketElementNum[j] = 0;
                }
            }
            index = 0;

        }


    }


}
