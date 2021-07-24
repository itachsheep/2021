/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.interview.腾讯;

import com.tao.algorithm.utils.Util;

class _quick_sort {
    public static void main(String[] args) {
        int[] arr = new int[]{9,8,3,23,45,1,245,76,68,0,5};
        Util.printArrays(arr);
//        quickSort(arr,0,arr.length - 1);
        quickSort2(arr,0,arr.length - 1);
        Util.printArrays(arr);
    }

    public static void quickSort2(int[] arr,int left,int right) {
        if(left >= right) {
            return;
        }

        int pivot = arr[right];
        int start = left , end = right;
        while (start < end) {
            while (start < end && arr[start] <= pivot){
                start++;
            }
            while (start < end && arr[end] >= pivot){
                end--;
            }
            swap(arr,start,end);
        }
        swap(arr,start,right);
        quickSort2(arr,left,start - 1);
        quickSort2(arr,start + 1, right);
    }

    public static void quickSort(int[] arr,int left,int right) {
        if(left >= right) {
            return;
        }

        int partition = partition(arr,left,right);

        quickSort(arr,left,partition - 1);
        quickSort(arr,partition + 1,right);
    }

    public static int partition(int[] arr,int left ,int right) {
        int pivot = arr[right];
        int start = left;
        int end = right;
        while (start < end) {
            while (start < end && arr[start] <= pivot)  {
                start++;
            }
            while (start < end && arr[end] >= pivot) {
                end--;
            }

            System.out.println("leftPtr: " + start+ ",rightPtr: " + end);
            swap(arr,start,end);
        }
        swap(arr,start,right);
        return start;
    }

    public static void swap(int[] arr,int left,int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
