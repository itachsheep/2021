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

public class _5_reverse_k_group {

    public static void main(String[] args) {
        ListNode list = Util.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        Util.printList(list);

        //Util.printList(reverseKGroup2(list,2));
        Util.printList(reverseKGroup2(list,3));
    }

    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse2(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public static ListNode[] myReverse2(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }


    public static ListNode reversKGroup(ListNode head, int k) {
        //在翻转子链表的时候，我们不仅需要子链表头节点 head，
        // 还需要有 head 的上一个节点 pre，以便翻转完后把子链表再接回 pre
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++){
                tail = tail.next;
                if(tail == null) {
                    return hair.next;
                }
            }

            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head,tail);
            head = reverse[0];
            tail = reverse[1];

            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return hair.next;
    }

    public static ListNode[] myReverse(ListNode head,ListNode tail) {
        ListNode pre = tail.next;
        ListNode p = head;
        while (pre != tail) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return new ListNode[]{tail,head};
    }
}
