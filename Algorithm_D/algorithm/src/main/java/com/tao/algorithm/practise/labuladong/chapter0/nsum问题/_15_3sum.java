/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.labuladong.chapter0.nsum问题;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class _15_3sum {

    public static void main(String[] args) {

    }

    /**
     * a + b + c = 0,
     * a + b = -c, 转为twoSum问题
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            twoSum(nums,list, i + 1, -nums[i]);
        }
        return list;
    }

    public static void twoSum(int[] nums,List<List<Integer>> list,
                              int start, int target) {
        int lo = start, hi = nums.length - 1;

        while (lo < hi) {
            if(nums[lo] + nums[hi] > target) {
                hi--;
            } else if(nums[lo] + nums[hi] < target) {
                lo++;
            } else {
                if(lo > 0 && nums[lo] == nums[lo -1]) {
                    continue;
                }
                if(hi > 0 && hi < nums.length - 1 && nums[hi] == nums[hi + 1]) {
                    continue;
                }
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[lo]);
                temp.add(nums[target]);
                temp.add(nums[hi]);
                list.add(temp);
            }

        }

    }

}
