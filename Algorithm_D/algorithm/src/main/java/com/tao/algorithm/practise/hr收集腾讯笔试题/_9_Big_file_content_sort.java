/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.hr收集腾讯笔试题;

/**
 * 大文件内容排序：一个20G大小的文件
 */
public class _9_Big_file_content_sort {

    /**
     * 设想你有一个20GB的文件，每行一个字符串，说明如何对这个文件进行排序。
     * 1，将20GB文件 拆分成n个 X MB大小文件，假设当前内存最大 m GB；
     * 2，对每个 x MB 文件进行排序（归并，快排，堆排）
     * 3，从每个 x MB文件中读书  m/n MB 的空间进行排序，将前 m/n MB的数据输出这是排好序的。
     * 4，如此，每次从 x MB 文件中读出 m/n MB的空间，直到，xMB文件读完。
     */
}
