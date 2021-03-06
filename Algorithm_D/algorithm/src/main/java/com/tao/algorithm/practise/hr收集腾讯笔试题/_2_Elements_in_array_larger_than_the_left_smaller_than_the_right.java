/**
 * @ClassName: _2_Elements_in_array_larger_than_the_left_smaller_than_the_right
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.hr收集腾讯笔试题;

import com.tao.algorithm.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 以时间复杂度O(n)从长度为n的数组中找出同时满足下面两个条件的所有元素：
 * （1）该元素比放在它左边的所有元素都大；
 * （2）该元素比放在它右边的所有元素都小。
 *
 * 输入：一个数组
 *
 * 输出：返回一个数组，数组中保存的是符合条件的元素的下标。
 */
public class _2_Elements_in_array_larger_than_the_left_smaller_than_the_right {
    public static void main(String[] args) {

        int[] test1 = {3, 5, 4, 2, 1, 6, 8, 7};
        int[] test2 = {1,2, 3, 4, 5, 6, 7, 8};
        int[] test3 = {1,2,3,1,2,0,5,6};

        Util.printArrayList(findElements(test1));
        Util.printArrayList(findElements(test2));
        Util.printArrayList(findElements(test3));
    }

    /**
     * https://52heartz.top/articles/algorithm-find-elements-before-which-all-the-elements-are-smaller-than-it-and-after-which-all-are-greater/
     *
     * 一个数要比它左边的所有数要大，比右边的所有数要小，那么它必定大于左边元素的最大值，同时小于右边元素的最小值。
     *
     * 两次遍历。第一次遍历从后向前，找出第 i 个元素右边元素的最小值，保存在 rightMin 数组中。
     * 第二次遍历，从前向后，使用一个临时变量保存左边元素的最大值。一边判断一边更新。
     * @param nums
     * @return
     */
    public static List<Integer> findElements(int[] nums){
        List<Integer> result = new ArrayList<>();
        int[] rightMin = new int[nums.length];

        int min = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; --i) {
            rightMin[i] = min;
            if(nums[i] < min) {
                min = nums[i];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] > max) {
                max = nums[j];
                if(nums[j] < rightMin[j]) {
                    result.add(nums[j]);
                }
            }
        }

        return result;
    }
}
