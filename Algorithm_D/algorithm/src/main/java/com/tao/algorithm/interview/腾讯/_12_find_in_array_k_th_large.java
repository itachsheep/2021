/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.interview.腾讯;

public class _12_find_in_array_k_th_large {
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
    public static void findKthLargest(int[] nums,int k) {

    }
}
