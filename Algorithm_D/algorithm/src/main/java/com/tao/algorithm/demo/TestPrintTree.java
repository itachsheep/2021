/***********************************************************
 * * Copyright (C), 2021-2029, OPPO Mobile Comm Corp., Ltd.
 * * VENDOR_EDIT
 * * File: TestTree
 * * Description: TestTree
 * * Version: 
 * * Date: 2021/3/4 21:12
 * * Author: Wei.Tao@oppo.com
 * *
 * * ---------------------Revision History: ---------------------
 * * <author>   <data>   <version>   <desc>
 * *
 * * Taowei   2021/3/4    
 ****************************************************************/
package com.tao.algorithm.demo;

import com.tao.algorithm.utils.TreeNode;
import com.tao.algorithm.utils.Util;

public class TestPrintTree {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        TreeNode root = Util.createBinTree(array);
        Util.printTree(root);
    }


}
