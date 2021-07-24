/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.labuladong.chapter0.区间问题;

import java.util.Arrays;
import java.util.Comparator;

class _1288_removeCoveredIntervals {

    /**
     * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     *
     * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
     *
     * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
     *
     *  
     *
     * 示例：
     *
     * 输入：intervals = [[1,4],[3,6],[2,8]]
     * 输出：2
     * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     *
     *
     *
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t0, int[] t1) {
                return t0[0] == t1[0] ? t1[1] - t0[1] : t0[0] - t1[0];
            }
        });

        int count = 0;
        int left = 0 , right = 0;
        for (int[] curr:intervals) {
            right = curr[1];
            if(left < right) {
                //当前区间的左边界 大于 上个区间的 右边界，肯定是不会完全覆盖的
                ++count;
                left = right;
            }
        }
        return count;
    }
}
