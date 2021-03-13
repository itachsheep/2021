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

public class _1_valid_bst_tree {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        TreeNode root = Util.createBinTree(array);
        Util.printTree(root);

        System.out.println("inOrder: ");
        inOrder(root);
    }

    /**
     * 合法二叉搜索树：左子树如果存在，则左子树小于根节点，右子树如果存在，则大于根节点：
     *
     */
    public static boolean isValidBST(TreeNode root) {
        if(root == null) {
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && pre.val > root.val) {
                return false;
            }
            pre = root;
            root = root.right;

        }
        return true;
    }

    /**
     * 非递归实现中序遍历
     * @param root
     */
    public static void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()){
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }
}
