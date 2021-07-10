/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.interview.腾讯;

import java.util.Random;

public class _12_find_in_array_k_th_large {
    static Random random = new Random();
    public static void main(String[] args) {
        int a = 1, b = 2;
    }

    /**
     * 采用快排的方案，对快排进行优化
     *
     *在分解的过程当中，我们会对子数组进行划分，如果划分得到的 qq 正好就是我们需要的下标，
     * 就直接返回 a[q]a[q]；否则，如果 qq 比目标下标小，就递归右子区间，
     * 否则递归左子区间。这样就可以把原来递归两个区间变成只递归一个区间，
     * 提高了时间效率。这就是「快速选择」算法。
     *
     */
    public static int findKthLargest(int[] nums,int k) {
        return quickSelect(nums,0,nums.length-1,nums.length - k);
    }

    public static int quickSelect(int[] a,int l, int r,int index) {
        int q = randomPartition(a,l,r);
        if(q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a,q+1,r,index)
                    : quickSelect(a,l,q-1,index);
        }
    }

    public static int randomPartition(int[] a,int l,int r) {
        int i = random.nextInt(r - l + 1) + 1;
        swap(a,i,r);
        return partition(a,l,r);
    }

    public static int partition(int[] a,int l,int r) {
        int x = a[r], i = l - 1;
        for(int j = 1; j < r; j++) {
            if(a[j] <= x) {
                swap(a,++i,j);
            }
        }
        swap(a,i+1,r);
        return i+1;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }



}
