/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

import com.tao.algorithm.utils.TreeNode;
import com.tao.algorithm.utils.Util;

class _test_buildMaxHeap {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,5,6,7,8,9};
        TreeNode root = Util.createTree(nums,0,null);
        Util.printTree(root);
        buildMaxHeap(nums,9);
        System.out.println(" --------------------------------------------- ");
        TreeNode heap = Util.createTree(nums,0,null);
        Util.printTree(heap);
    }

    public static int findKthLargest(int[] nums, int k) {
        return 0;
    }

    public static void buildMaxHeap(int[] a,int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a,i,heapSize);
        }
    }

    public static void maxHeapify(int[] a,int i,int heapSize) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;

        if(l < heapSize && a[l] > a[largest]) {
            largest = l;
        }

        if(r < heapSize && a[r] > a[largest]) {
            largest = r;
        }

        if(largest != i) {
            swap(a,largest,i);
            maxHeapify(a,largest,heapSize);
        }
    }

    private static void swap(int[] a,int m, int n) {
        int temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }
}
