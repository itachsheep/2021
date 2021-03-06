/**
 * @ClassName: _5_binary_tree_traversal
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.hr收集腾讯笔试题;

import com.tao.algorithm.utils.TreeNode;
import com.tao.algorithm.utils.Util;

public class _5_binary_tree_traversal {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        TreeNode root = Util.createBinTree(arr);
        Util.printTree(root);

        System.out.println("先序遍历：");
        preOrder(root);
        System.out.println();

        System.out.println("中序遍历：");
        inOrder(root);
    }

    public static void preOrder(TreeNode root){
        if(root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void inOrder(TreeNode root){
        if(root != null) {
            preOrder(root.left);
            System.out.print(root.val + " ");
            preOrder(root.right);
        }
    }

}
