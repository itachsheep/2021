/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

import com.tao.algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，
 * 按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 */
class _10_binary_tree_right_slide_view {
    public static void main(String[] args) {

    }

    /**
     * 思路
     * 我们对树进行深度优先搜索，在搜索过程中，我们总是先访问右子树。
     * 那么对于每一层来说，我们在这层见到的第一个结点一定是最右边的结点。
     * 这样一来，我们可以存储在每个深度访问的第一个结点，一旦我们知道了树的层数，
     * 就可以得到最终的结果数组。
     *
     *
     */
    public static List<Integer> rightSideView(TreeNode root) {
        int maxDepth = -1;
        Map<Integer,Integer> rightMostValueAtDepth = new HashMap<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();

        nodeStack.push(root);
        depthStack.push(0);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if(node != null) {
                // 维护二叉树的最大深度
                maxDepth = Math.max(maxDepth,depth);
                // 如果不存在对应深度的节点我们才插入
                if(!rightMostValueAtDepth.containsKey(depth)) {
                    rightMostValueAtDepth.put(depth,node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }

        List<Integer> rightView = new ArrayList<>();
        for (int depth = 0; depth <= maxDepth; depth++) {
            rightView.add(rightMostValueAtDepth.get(depth));
        }
    }
}
