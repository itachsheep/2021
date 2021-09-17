/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *
 * 图示两个链表在节点 c1 开始相交：
 *
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 *
 *
 *
 *
 */
package com.tao.algorithm.practise.字节;

import com.tao.algorithm.utils.ListNode;

class _160_getInterSectionNode {

    public static void main(String[] args) {

    }

    /**
     * 分析思路
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
     * 假设链表A 长度 a + c = m;
     * 链表B 长度 b + c = n；
     * 如果有相同节点：
     * 则，指针pA 和 pB 分别移动 a + b + c 肯定能指向同一个节点
     *
     * 如果没有，则指针pA 和 pB 分别移动 m + n 同时变成null
     *
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}


