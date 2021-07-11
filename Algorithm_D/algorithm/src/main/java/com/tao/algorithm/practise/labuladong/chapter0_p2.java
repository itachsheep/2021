/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.labuladong;

public class chapter0_p2 {
    public static void main(String[] args) {

    }

    //todo: 5, 二分查找算法
    /**
     *
     */


    //todo: 6,滑动窗口算法
    /**
     * 常用场景：字符串包含问题
     *
     * 1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，
     * 把索引左闭右开区间 [left, right) 称为一个「窗口」。
     *
     * 2、我们先不断地增加 right 指针扩大窗口 [left, right)，
     * 直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
     *
     * 3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right)，
     * 直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，
     * 每次增加 left，我们都要更新一轮结果。
     *
     * 4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
     *
     */

    //todo: 7,股票买卖问题

    /**
     * 这个问题的「状态」有三个，第一个是天数，第二个是允许交易的最大次数，
     * 第三个是当前的持有状态（即之前说的 rest 的状态，我们不妨用 1 表示持有，0 表示没有持有）
     *
     * 用自然语言描述出每一个状态的含义，比如说 dp[3][2][1] 的含义就是：
     * 今天是第三天，至今最多进行 2 次交易,我现在手上持有着股票，
     *
     *
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *               max(   选择 rest  ,             选择 sell      )
     *
     * 解释：今天我没有持有股票，有两种可能：
     * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     *
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *               max(   选择 rest  ,           选择 buy         )
     *
     * 解释：今天我持有着股票，有两种可能：
     * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     *
     *把上面的状态转移方程总结一下：
     * base case：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     *
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     *
     */

    /**
     * 三、秒杀题目
     * 第一题，k = 1
     *
     *
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     *             = max(dp[i-1][1][1], -prices[i])
     * 解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
     *
     * 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
     * 可以进行进一步化简去掉所有 k：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     *
     *
     * int n = prices.length;
     * int[][] dp = new int[n][2];
     * for (int i = 0; i < n; i++) {
     *     dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
     *     dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
     * }
     * return dp[n - 1][0];
     *
     */

}
