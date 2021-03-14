/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

import com.tao.algorithm.utils.TreeNode;
import com.tao.algorithm.utils.Util;

import java.util.Stack;

public class _3_z_shape_print_Tree {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        TreeNode root = Util.createBinTree(array);
        Util.printTree(root);
        System.out.println("the tree in z shape: ");
        printTreeInZShape(root);
    }

    /**
     *
     *  * 请实现一个函数按照之字形打印二叉树，
     *  * 即第一行按照从左到右的顺序打印，
     *  * 第二层按照从右至左的顺序打印，
     *  * 第三行按照从左到右的顺序打印，其他行以此类推
     * https://blog.csdn.net/zhou15755387780/article/details/79228616
     * @param root
     */

    public static void printTreeInZShape(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        int index = 0;
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if(index % 2 == 1) {
                while (!stack2.isEmpty()) {
                    TreeNode node = stack2.pop();
                    if(node != null) {
                        System.out.print(node.val + " ");
                        stack1.push(node.right);
                        stack1.push(node.left);
                    }
                }
            } else {
                while (!stack1.isEmpty()) {
                    TreeNode node = stack1.pop();
                    if(node != null) {
                        System.out.print(node.val + " ");
                        stack2.push(node.left);
                        stack2.push(node.right);
                    }
                }
            }
            index++;
            System.out.println();
        }
    }
}
