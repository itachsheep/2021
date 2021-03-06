/**
 * @ClassName: quick_sort
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.hr收集腾讯笔试题;

import com.tao.algorithm.utils.Util;

/**
 * 快速排序
 */
public class _10_quick_sort {

    public static void main(String[] args) {
        int[] arr1 = {9, 23, 4, 32, 3, 1, 54, 7, 76, 34, 78, 89, 6, 45};
        int[] arr2 = {34, 45, 667, 23, 4, 56, 67, 8, 89, 6, 45};
        int[] arr3 = {10, 9, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("-------- quick sort ---------");
        quickSort(arr1, 0, arr1.length - 1);
        quickSort(arr2, 0, arr2.length - 1);
        quickSort(arr3, 0, arr3.length - 1);
        Util.printArrays(arr1);
        Util.printArrays(arr2);
        Util.printArrays(arr3);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int partition = partition(arr, left, right);
        //System.out.println("partition " + partition);
        quickSort(arr, left, partition - 1);
        quickSort(arr, partition + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = right;
        while (left < right) {
            if (arr[left] <= arr[pivot] && left < right) {
                left++;
            }
            if (arr[right] > arr[pivot] && left < right) {
                right--;
            }
            swap(arr, left, right);
        }
        return left;
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
