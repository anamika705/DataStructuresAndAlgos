package com.cracking.coding.interview.string.and.arrays.basics.tree.and.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Trees {
    public static class Node{
        private int data;
        private Node left;
        private Node right;
        public Node(int data){
            this.data = data;
            left = right = null;
        }

        public void inorder(Node head){
            if (head == null)
                return;
            inorder(head.left);
            System.out.print( head.data + " ");
            inorder(head.right);
        }

        public Node mirrorRecursive(Node head){
            if(head == null){
                return head;
            }
            Node left = mirrorRecursive(head.left);
            Node right = mirrorRecursive(head.right);
            head.left = right;
            head.right = left;
            return head;
        }

        public void mirror(Node head){
            Queue<Node> q = new LinkedList<>();
            q.add(head);
            while(!q.isEmpty()){
                Node curr = q.poll();
                Node temp = curr.left;
                curr.left = curr.right;
                curr.right= temp;
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
        }

        public int depthOfTree(Node head){
            if(head == null){
                return 0;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(head);
            int leftDistance = 0;
            int rightDistance = 0;
            while(!q.isEmpty()){
                Node curr = q.poll();
                if(curr.left != null){
                    leftDistance++;
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                    rightDistance++;
                }

            }
            return Math.max(leftDistance, rightDistance) + 1;
        }
    }

    public static int maxDepth(Node head){
        if(head == null){
            return 0;
        }
        int leftDepth = maxDepth(head.left);
        int rightDepth = maxDepth(head.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(6);


        System.out.println(maxDepth(root));


    }
}
