/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.字节;

import com.tao.algorithm.utils.ListNode;
import com.tao.algorithm.utils.Util;


public class _2_linked_list_odd_ascending_even_descending {
    /**
     * 一个有序链表，奇数位升序，偶数位降序
     * 1，将链表拆分成奇数位 偶数位两个链表
     * 2，偶数位反转
     * 3，两个链表合并
     * @param args
     */
    public static void main(String[] args) {
        ListNode head = Util.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Util.printList(head);
        System.out.println("result: ");
        Util.printList(arrangeLinkedList(head));

    }

    public static ListNode arrangeLinkedList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        int i = 3;
        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode oddNode = oddHead, evenNode = evenHead;
        ListNode p = evenHead.next;
        while (p != null) {
            if(i % 2 == 1) {
                oddNode.next = p;
                oddNode = oddNode.next;
            } else {
                evenNode.next = p;
                evenNode = evenNode.next;
            }
            System.out.println("i： " + i+ "， p:" + p.val);
            p = p.next;
            i++;
        }
        oddNode.next = null;
        evenNode.next = null;

        ListNode reversedEvenHead = reverse(evenHead);

        System.out.println("reversedEvenHead: ");
        Util.printList(reversedEvenHead);

        ListNode newHead = oddHead;
        ListNode cur = oddHead;
        oddHead = oddHead.next;
        i = 2;
        while (oddHead != null) {
            if(i % 2 == 0) {
                cur.next = reversedEvenHead;
                reversedEvenHead = reversedEvenHead.next;
            } else {
                cur.next = oddHead;
                oddHead = oddHead.next;

            }
            cur = cur.next;
            i++;
        }
        return newHead;
    }

    public static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
