package com.cracking.coding.interview.string.and.arrays;


import com.data.structures.model.ListNode;

import java.util.HashSet;

public class LinkedLists {
    public static class Node{
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
        }

        public static void main(String[] args) {
            Node node1 = new Node(7);
            node1.next = new Node(1);
            node1.next.next = new Node(6);

            Node node2 = new Node(5);
            node2.next = new Node(9);
            node2.next.next = new Node(2);
//            node.next.next.next = new Node(5);
//            node.next.next.next.next = new Node(8);
//            node.next.next.next.next.next = new Node(1);
//            node.next.next.next.next.next.next = new Node(7);
            sumLists(node1, node2);
        }

        public static Node removeDuplicates(Node node){
               Node ptr1 = node;
               Node ptr2 = null;
               while(ptr1 != null && ptr1.next != null){
                   ptr2 = ptr1;
                   while(ptr2.next != null){
                      if(ptr1.data == ptr2.next.data){
                          ptr2.next = ptr2.next.next;
                      }else{
                          ptr2 = ptr2.next;
                      }
                   }
                   ptr1 = ptr1.next;
               }
               return node;
        }

        public static Node removeDuplicatesUsingHashSet(Node node){
           Node prev = null;
           Node curr = node;
           HashSet<Integer> nodeSet = new HashSet<>();
           while(curr != null){
               if(nodeSet.contains(curr.data)){
                   prev.next = curr.next;
               }else{
                   nodeSet.add(curr.data);
                   prev = curr;
               }
               curr = curr.next;
           }
           return node;
        }

        public static int getKthToTheLast(Node node, int k){
            if(node == null){
                return 0;
            }
            Node curr = node;
            while(k > 0){
                curr = curr.next;
                k--;
            }
            Node sec = node;
            while(curr != null){
                curr = curr.next;
                sec = sec.next;
            }
            return sec.data;
        }

        public static Node deleteMiddleElement(Node node){
            if (node == null)
                return null;
            if (node.next == null) {
                return null;
            }

            Node fast_ptr = node;
            Node slow_ptr = node;
            Node prev = null;
            while(fast_ptr != null && fast_ptr.next != null){
                fast_ptr = fast_ptr.next.next;
                prev = slow_ptr;
                slow_ptr = slow_ptr.next;
            }
            prev.next = slow_ptr.next;
            return node;
        }

        public static Node partitionLinkedList(Node node, int x){
            if(node == null){
                return null;
            }
            Node before_partition = new Node(0);
            Node before = before_partition;
            Node after_partition = new Node(0);
            Node after = after_partition;
            while(node != null){
                if(node.data < x){
                    before.next = node;
                    before  = before.next;
                }else{
                    after.next = node;
                    after = after.next;
                }
                node = node.next;
            }
            after.next = null;
            before.next = after_partition.next;
            return before_partition.next;
        }

        public static Node addLists(Node head1, Node head2){
            int val1 = 0;
            int val2 = 0;
            int carry = 0;
            int sum = 0;
            Node sumNode = new Node(0);
            Node l3  = sumNode;
            while(head1 != null || head2 !=null){
                if(head1 != null){
                   val1 = head1.data;
                   head1 = head1.next;
                }else{
                   val1 = 0;
                }
                if(head2 != null){
                    val2 = head2.data;
                    head2 = head2.next;
                }else{
                    val2 = 0;
                }
                sum = val1 + val2 + carry;
                carry = sum /10;
                sum = sum % 10;
                Node temp = new Node(sum);
                l3.next = temp;
                l3 = l3.next;
            }
            if(carry != 0){
                Node temp = new Node(carry);
                l3.next = temp;
            }
            return sumNode.next;
        }

        public static Node sumLists(Node head1, Node head2){
            Node reverseHead1 = reverseSinglyLinkedList(head1);
            Node reverseHead2 = reverseSinglyLinkedList(head2);
            String num1 = "";
            String num2 = "";
            while(reverseHead1 != null){
                 num1 += reverseHead1.data;
                reverseHead1 = reverseHead1.next;
            }
            while(reverseHead2 != null){
                num2 += reverseHead2.data;
                reverseHead2 = reverseHead2.next;
            }
            String sum = String.valueOf((Integer.valueOf(num1) + Integer.valueOf(num2)));
            int size = sum.length();
            Node reversedNode = new Node(Character.getNumericValue(sum.charAt(size - 1)));
            Node curr = reversedNode;
            for(int i=sum.length()-2; i>=0; i--){
                curr.next = new Node(Character.getNumericValue(sum.charAt(i)));
                curr = curr.next;
            }
            return reversedNode;
        }

        private static Node reverseSinglyLinkedList(Node head){
            Node prev = null;
            Node curr = head;
            Node next = null;
            while(curr != null){
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            head = prev;
            return head;
        }

    }
}
