/**
 * @ClassName: MergeSort
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.hr收集腾讯笔试题;

import com.tao.algorithm.utils.Util;

public class _11_merge_sort {
    public static void main(String[] args) {
        int[] arr1 = {9, 23, 4, 32, 3, 1, 54, 7, 76, 34, 78, 89, 6, 45};
        int[] arr2 = {34, 45, 667, 23, 4, 56, 67, 8, 89, 6, 45};
        int[] arr3 = {10, 9, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        mergeSort(arr1, 0, arr1.length - 1);
        mergeSort(arr2, 0, arr2.length - 1);
        mergeSort(arr3, 0, arr3.length - 1);
        Util.printArrays(arr1);
        Util.printArrays(arr2);
        Util.printArrays(arr3);
    }

    /**
     * _11_归并排序
     *
     * https://blog.csdn.net/jianyuerensheng/article/details/51262984
     * 1，将数组无线等分 分成两个，直到每个单元数组只剩下一个元素
     * 2，将单元数组按照从小到大顺序合并
     * 3，最终形成排列好的数组
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void mergeSort(int[] arr,int low,int high) {
        int mid = (low + high) / 2;
        if(low < high) {//最终拆分成一个元素的单元数组，low == high结束
            mergeSort(arr,low,mid);
            mergeSort(arr,mid + 1,high);
            merge(arr,low,mid,high);
        }
    }

    public static void merge(int[] arr,int low,int mid,int high) {
        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid + 1;
        int index = 0;
        while (left <= mid && right <= high) {
            if(arr[left] <= arr[right]) {
                temp[index++] = arr[left++];
            } else {
                temp[index++] = arr[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = arr[left++];
        }

        while (right <= high ){
            temp[index++] = arr[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }
}
