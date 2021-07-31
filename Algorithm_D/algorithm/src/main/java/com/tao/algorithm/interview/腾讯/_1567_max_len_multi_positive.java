/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.interview.腾讯;

class _1567_max_len_multi_positive {
    /**
     * 1567. 乘积为正数的最长子数组长度
     * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
     *
     * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
     *
     * 请你返回乘积为正数的最长子数组长度。
     *
     *
     *
     * 示例  1：
     *
     * 输入：nums = [1,-2,-3,4]
     * 输出：4
     * 解释：数组本身乘积就是正数，值为 24 。
     */
    public static void main(String[] args) {

    }

    /**
     * 可以使用动态规划得到乘积为正数的最长子数组长度。定义两个数组 positive 和 negative，
     * 其中 positive[i] 表示以下标 ii 结尾的乘积为正数的最长子数组长度，
     * negative[i] 表示乘积为负数的最长子数组长度。
     *
     *当  i>1 时，根据  nums[i] 的值计算  positive[i] 和  negative[i] 的值。
     *
     * 当  nums[i]>0 时，之前的乘积乘以  nums[i] 不会改变乘积的正负性。
     *      pos[i] = pos[i-1] + 1
     *
     *      neg[i] = neg[i - 1] + 1 或者
     *      neg[i] = 0, neg[i-1] = 0;
     *
     * 当  nums[i] < 0 时，之前的乘积乘以  nums[i] 会改变乘积的正负性。
     *      pos[i]  和 neg[i] 跟上面相反
     */
    public static int getMaxLen(int[] nums) {

    }
}
