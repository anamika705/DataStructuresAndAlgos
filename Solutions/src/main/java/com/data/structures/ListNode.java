package com.data.structures;

import com.cracking.coding.interview.string.and.arrays.LinkedLists;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    static void deleteNode(ListNode node){
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while(fast.next != null){
            fast = fast.next;
            if(n-- <= 0){
                slow = slow.next;
            }
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static ListNode getNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while(fast.next != null ){
            fast = fast.next;
            if(n-- >= 0){
                slow = slow.next;
            }
        }
        return slow;
    }


    public static ListNode deleteMiddleElement(ListNode node){
        ListNode dummy = new ListNode(0);
        dummy.next = node;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static ListNode partitionLinkedList(ListNode node, int x){
        ListNode dummy = new ListNode(0);
        ListNode after = dummy;
        ListNode dummy2 = new ListNode(0);
        ListNode before = dummy2;
        while(node != null){
            if(node.val > x){
                after.next = node;
                after = after.next;
            }else{
                before.next = node;
                before = before.next;
            }

            node = node.next;
        }
        after.next = null;
        before.next = dummy.next;
        return dummy2.next;
    }

    public static ListNode reverseList(ListNode head) {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while(l1 !=null && l2 != null){
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
        return dummy.next;
    }

    public static boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHalf = reverseList(slow.next);
        ListNode firstHalf = head;
        while(firstHalf != null && secondHalf != null){
            if(firstHalf.val != secondHalf.val){
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null &&  fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;
        int carry=0, val1=0, val2=0;
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
            int temp = val1 + val2 + carry;
            carry = temp / 10;
            int sum = temp % 10;
            ListNode tempNode = new ListNode(sum);
            sumNode.next = tempNode;
            sumNode = sumNode.next;
        }
        if(carry != 0){
            ListNode tempNode = new ListNode(carry);
            sumNode.next = tempNode;
        }
        return dummy.next;
    }


    public static ListNode oddEvenList(ListNode head) {
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenCopy = even;
        while(even!=null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenCopy;
        return head;
    }


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


    public static void main(String[] args) {
        ListNode node = new ListNode(9);
        node.next = new ListNode(1);
        node.next.next = new ListNode(8);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);


        ListNode node1 = new ListNode(5);
        node1.next = new ListNode(6);
        node1.next.next = new ListNode(1);
        node1.next.next.next = new ListNode(8);
        node1.next.next.next.next = new ListNode(4);
        node1.next.next.next.next.next = new ListNode(5);

        partitionLinkedList(node, 4);
    }
}
