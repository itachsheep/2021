/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.labuladong;

public class chapter0 {

    public static void main(String[] args) {
        //todo:1, 刷二叉树
            //各种搜索问题其实都是树的遍历问题

        //todo:2，动态规划
            // 动态规划问题的第一个性质：重叠子问题
            // 要符合「最优子结构」，子问题间必须互相独立
            //


        //todo: 3，回溯法
        /**
         * 解决一个回溯问题，实际上就是一个决策树的遍历过程。你只需要思考 3 个问题：
         * 1、路径：也就是已经做出的选择。
         * 2、选择列表：也就是你当前可以做的选择。
         * 3、结束条件：也就是到达决策树底层，无法再做选择的条件。
         */


        //todo: BFS算法问题
        /**
         * 出现的常见场景，问题的本质就是让你在一幅「图」中找到
         * 从起点 start 到终点 target 的最近距离，最短路径？？dijs算法
         *
         * 1，核心数据结构queue：
         * 2，避免走回头路set：visited
         * 3，将起点加入队列
         * 4，记录扩散的步数
         * 5，将当前队列中的所有节点向四周扩散
         * 6，划重点：这里判断是否到达终点
         * 7，将 cur 的相邻节点加入队列
         * 8，划重点：更新步数在这里
         *
         */
    }
}
