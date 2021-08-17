package com.data.structures;

import com.data.structures.model.ListNode;


public class LinkedListDS {
    /**
     * Write a function to delete a node in a singly-linked list. You will not be given access to the head of the list, instead you will be given access to the node to be deleted directly.
     * It is guaranteed that the node to be deleted is not a tail node in the list.
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    /**
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy; // Move slow pointer when difference between slow and fast pointer is n
        ListNode fast = dummy; // Move fast pointer first
        while(fast.next != null){
            fast = fast.next;
            if(n-- <= 0){
                slow = slow.next;
            }
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


    /**
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
     */
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    /**
     * Merge two sorted linked lists and return it as a sorted list.
     * The list should be made by splicing together the nodes of the first two lists.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       ListNode prev = new ListNode(0);
       ListNode curr = prev;
       while(l1 != null && l2 != null){
           if(l1.val < l2.val){
               curr.next = l1;
               l1 = l1.next;
           }else{
               curr.next = l2;
               l2 = l2.next;
           }
           curr = curr.next;
       }
       curr.next = l1 == null ? l2 : l1;
       return prev.next;
    }


    /**
     * Given the head of a singly linked list, return true if it is a palindrome.
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHalf = reverse(slow.next);
        ListNode firstHalf = head;
        while(firstHalf!=null && secondHalf!=null){
            if(secondHalf.val != firstHalf.val){
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    private ListNode reverse(ListNode node){
        ListNode curr = node;
        ListNode prev = null;
        ListNode next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    /**
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     *
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
     *
     * Return true if there is a cycle in the linked list. Otherwise, return false
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }


    /***************************** Medium problem starts  ******************************/

    /**
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int val1 = 0;
        int val2 = 0;
        int sum = 0;
        int carry = 0;
        ListNode sumNode = new ListNode(0);
        ListNode l3  = sumNode;
        while(l1 != null || l2 != null){
           if(l1 != null){
               val1 = l1.val;
               l1 = l1.next;
           }else{
               val1 = 0;
           }
            if(l2 != null){
                val2 = l2.val;
                l2 = l2.next;
            }else{
                val2 = 0;
            }
            sum = val1 + val2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode temp = new ListNode(sum);
            l3.next = temp;
            l3 = l3.next;
        }
        if(carry != 0){
            ListNode temp = new ListNode(carry);
            l3.next = temp;
        }
        return sumNode.next;
    }


    /**
     * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
     *
     * The first node is considered odd, and the second node is even, and so on.
     *
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     *
     * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
     *
     * Video Ref: https://www.youtube.com/watch?v=MUeRdqRXUUo
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenCopy = even;
        if(head == null){
            return head;
        }
        while(even!=null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenCopy;
        return head;
    }

/**
 * https://www.youtube.com/watch?v=u4FWXfgS8jw
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 */
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode listA = headA;
    ListNode listB = headB;
    while(listA != listB){
        listA = listA == null ? headB : listA.next;
        listB = listB == null ? headA : listB.next;
    }
    return listA;
}

}
