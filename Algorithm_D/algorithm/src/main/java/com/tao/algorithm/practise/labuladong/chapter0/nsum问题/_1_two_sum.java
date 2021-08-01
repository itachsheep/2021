/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.labuladong.chapter0.nsum问题;

import java.util.Arrays;
import java.util.HashMap;

class _1_two_sum {
    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * 1，使用hash表
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(target - nums[i])) {
                return new int[]{nums[i],target - nums[i]};
            } else {
                hashMap.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 2，使用拉不拉咚方法，先排序，再使用双指针
     */
    public int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int sum = nums[low] + nums[high];
            if(sum < target) {
                low++;
            } else if(sum > target) {
                high--;
            } else {
                return new int[]{nums[low], nums[high]};
            }
        }
        return new int[] {-1, -1};
    }
}
