package Algorithm;

import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        int[] array = {3, 1, 5, 6, 4, 8, 7, 0, 9, 2};
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1,temp);
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
                int index = i;
                int temp = array[index];
                while (index - gap >= 0 && temp < array[index - gap]) {
                    array[index] = array[index - gap];
                    index -= gap;
                }
                array[index] = temp;
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
                array[minIndex] = array[i];
                array[i] = min;
            }

        }

    }

    public static void quickSort(int[] array, int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int temp;
        int midValue = array[low];
        while (i < j) {
            while (i < j && array[j] >= midValue) {
                j--;
            }
            while (i < j && array[i] <= midValue) {
                i++;
            }
            if (i < j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        array[low] = array[i];
        array[i] = midValue;
        quickSort(array, low, i - 1);
        quickSort(array, i + 1, high);
    }


    public  static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            merge(array,left,mid,right,temp);
        }
    }

    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right){
            if (array[i] < array[j]){
                temp[index++] = array[i++];
            }else {
                temp[index++] = array[j++];
            }
        }
        while (i<= mid){
            temp[index++] = array[i++];
        }
        while (j <= right){
            temp[index++] = array[j++];
        }

        index = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            array[tempLeft++] = temp[index++];
        }
    }
}
