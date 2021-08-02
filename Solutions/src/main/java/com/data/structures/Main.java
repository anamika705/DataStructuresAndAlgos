package com.data.structures;



public class Main {
    public static void main(String[] args) {
        StringDS strDS = new StringDS();
       /* TreeNode tree = new TreeNode(1);
        tree.setLeft(new TreeNode(2));
        tree.setRight(new TreeNode(3));
        tree.getLeft().setLeft(new TreeNode(4));
        tree.getLeft().setRight(new TreeNode(5));*/
        int[] nums = {1,3,3,5,6,7,7,9}; int[] inorder = {9,3,15,20,7};

        System.out.println(strDS.longestPalindrome("babad"));
    }


}

