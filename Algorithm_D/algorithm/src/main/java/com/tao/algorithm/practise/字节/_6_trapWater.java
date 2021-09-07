/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，
 * 可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 */
public class _6_trapWater {
    public static void main(String[] args) {
       int[] height = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
       System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int n = height.length;
        if(n == 0) return 0;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1],height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1],height[j]);
        }

        int res = 0;
        for (int i = 0; i < n ; i++) {
            res += Math.min(leftMax[i],rightMax[i]) - height[i];
        }
        return res;
    }
}
