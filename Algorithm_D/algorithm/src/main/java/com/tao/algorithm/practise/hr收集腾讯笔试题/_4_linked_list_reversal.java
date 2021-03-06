/**
 * @ClassName: _4_linked_list_reversal
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.hr收集腾讯笔试题;

import com.tao.algorithm.utils.ListNode;
import com.tao.algorithm.utils.Util;

/**
 * 链表反转
 */
public class _4_linked_list_reversal {
    public static void main(String[] args) {
        int[] num = {1,2,3,4,5,6,7,8,9};
        ListNode head = Util.createList(num);
        Util.printList(head);
        ListNode reverse = reverse(head);
        Util.printList(reverse);

    }

    public static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
