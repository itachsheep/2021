/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.interview.腾讯;

import com.tao.algorithm.utils.ListNode;

class _148_sort_list {
    public static void main(String[] args) {

    }

    /**
     *
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * 输入：head = [4,2,1,3]
     * 输出：[1,2,3,4]
     * @param head
     * @return
     */

    /**
     * 方法一：自顶向下归并排序
     * 对链表自顶向下归并排序的过程如下。
     *
     * 找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动 22 步，慢指针每次移动 11 步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
     *
     * 对两个子链表分别排序。
     *
     * 将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，将两个有序的子链表进行合并。
     *
     * 上述过程可以通过递归实现。递归的终止条件是链表的节点个数小于或等于 11，即当链表为空或者链表只包含 11 个节点时，不需要对链表进行拆分和排序
     */
    public  static ListNode sortList(ListNode head) {
        return sortList(head,null);
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if(head == null) {
            return head;
        }

        if(head.next == tail) {
            head.next = null;
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if(fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head,mid);
        ListNode list2 = sortList(mid,tail);
        ListNode sorted = merge(list1,list2);
        return sorted;
    }

    public static ListNode merge(ListNode head,ListNode tail) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        while (head != null && tail != null) {
            if(head.val <= tail.val) {
                temp.next = head;
                head = head.next;
            } else {
                temp.next = tail;
                tail = tail.next;
            }
        }
        if(head != null) {
            temp.next = head;
        }
        if(tail != null) {
            temp.next = tail;
        }
        return dummyHead.next;
    }

}
