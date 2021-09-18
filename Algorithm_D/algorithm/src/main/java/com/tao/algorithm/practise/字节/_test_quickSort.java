/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

import com.tao.algorithm.utils.Util;

class _test_quickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9,8,3,23,45,1,245,76,68,0,5};
        Util.printArrays(arr);
        quickSort(arr,0,arr.length - 1);
        Util.printArrays(arr);
    }

    public static void quickSort(int[] arr,int left ,int right) {
        if(left >= right) {
            return;
        }

        int pivot = arr[right];
        int start = left, end = right;
        while (start < end) {
            while (start < end && arr[start] <= pivot) {
                start++;
            }

            while (start < end && arr[end] >= pivot) {
                end--;
            }
            swap(arr,start,end);
        }
        swap(arr,start,right);
        quickSort(arr,left,start - 1);
        quickSort(arr, start + 1,right);
    }

    public static void swap(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
}
