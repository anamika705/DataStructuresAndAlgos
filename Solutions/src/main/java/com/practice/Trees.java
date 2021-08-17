package com.practice;

import java.util.ArrayList;
import java.util.Scanner;

public class Trees {
        static class Node {
            int value;
            Node left, right;

            Node(int value){
                this.value = value;
                left = null;
                right = null;
            }
        }

        public void insert(Node node, int value) {
            if (value < node.value) {
                if (node.left != null) {
                    insert(node.left, value);
                } else {
                    System.out.println(" Inserted " + value + " to left of " + node.value);
                    node.left = new Node(value);
                }
            } else if (value > node.value) {
                if (node.right != null) {
                    insert(node.right, value);
                } else {
                    System.out.println("  Inserted " + value + " to right of " + node.value);
                    node.right = new Node(value);
                }
            }
        }


        public static void main(String args[])
        {
            Trees tree = new Trees();
            System.out.println("Please provide inputs for the tree nodes");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String[] values = userInput.split(" ");
            Node root = new Node(Integer.parseInt(values[0]));
            for(int i = 1; i < values.length; i++){
                tree.insert(root, Integer.parseInt(values[i]));
            }
            System.out.println(root);
        }
}
