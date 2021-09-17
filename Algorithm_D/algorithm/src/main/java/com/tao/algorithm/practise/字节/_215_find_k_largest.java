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

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
class _215_find_k_largest {
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
