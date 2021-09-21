/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

/**
 * 674. 最长连续递增序列
 *
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，
 * 如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 *
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 *
 *
 */
class _674_findLengthOfLCIS {
    public static void main(String[] args) {

    }

    /**
     * 为了得到最长连续递增序列，可以使用贪心的策略得到尽可能长的连续递增序列。
     * 做法是使用记录当前连续递增序列的开始下标和结束下标，
     * 遍历数组的过程中每次比较相邻元素，
     * 根据相邻元素的大小关系决定是否需要更新连续递增序列的开始下标。
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int start = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if(i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            ans = Math.max(ans,i - start + 1);
        }
        return ans;
    }
}
