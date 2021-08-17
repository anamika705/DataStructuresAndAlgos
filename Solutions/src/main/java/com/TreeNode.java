package com;

import com.data.structures.TreeDS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

    public static int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }


    public static  boolean isValidBST(TreeNode root) {
       return isValidBinaryTree(root, null, null);
    }
    private static boolean isValidBinaryTree(TreeNode root, Integer max, Integer min){
          if(root == null){
              return true;
          }
          if(max != null && root.val >= max || min != null && root.val <= min){
              return false;
          }
          return isValidBinaryTree(root.left, root.val, min) && isValidBinaryTree(root.right, max, root.val);
    }


    public static boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public static boolean isSymmetric(TreeNode left, TreeNode right) {
          if(left == null || right == null){
              return left == right;
          }
          if(left.val != right.val){
              return false;
          }
          return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }


    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> tree = new ArrayList<>();
        if(root == null){
            return tree;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> currentlist = new ArrayList<>();
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode current = queue.remove();
                currentlist.add(current.val);
                if(current.left != null){
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
            }
            tree.add(currentlist);
        }
        return tree;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if(root == null){
            return nodes;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.add(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            nodes.add(curr.val);
            curr = curr.right;
        }
        return nodes;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<>();
        if(root == null){
            return nodes;
        }
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            count++;
            int size = queue.size();
            List<Integer> currList = new ArrayList<>();
            for(int i=0; i<size; i++){
                TreeNode curr = queue.remove();
                currList.add(curr.val);
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            if(count % 2 == 0){
                Collections.reverse(currList);
            }
            nodes.add(currList);
        }
        return nodes;
    }


    static class Solution{
            HashMap<Integer, Integer> map = new HashMap<>();
            int preIndex = 0;
            public  TreeNode buildTree ( int[] preorder, int[] inorder){

            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, inorder, 0, preorder.length - 1);
        }

            private  TreeNode buildTree ( int[] preorder, int[] inorder, int start, int end){
            if (start > end) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[preIndex++]);
            if (root == null) {
                return null;
            }
            if (start == end) {
                return root;
            }
            int index = map.get(root.val);
            root.left = buildTree(preorder, inorder, start, index - 1);
            root.right = buildTree(preorder, inorder, index + 1, end);
            return root;
        }
    }




    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root) {
          if(root == null){
              return null;
          }
          Queue<Node> queue = new LinkedList<>();
          queue.add(root);
          queue.add(null);
          while(!queue.isEmpty()){
              Node curr = queue.poll();
              if(curr == null && queue.isEmpty()){
                  return root;
              }
              else if(curr == null){
                  queue.add(null);
                  continue;
              }else{
                  curr.next = queue.peek();
                  if(curr.left != null){
                      queue.add(curr.left);
                  }
                  if(curr.right != null){
                      queue.add(curr.right);
                  }
              }
          }
          return root;
    }




    public static int kthSmallest(TreeNode root, int k) {
        int[] nums = new int[2];
        inorder(root, nums, k);
        return nums[1];
    }
    private static void inorder(TreeNode root, int[] nums, int k){
          if(root == null){
              return;
          }
        inorder(root.left, nums, k);
          if(++nums[0] == k){
              nums[1] = root.val;
              return;
          }
        inorder(root.right, nums, k);
    }


    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        while(p.right != null){
            return findMinChild(p.right);
        }
        TreeNode succ = null;
        while(root != null){
            if(root.val > p.val){
                succ = root;
                root = root.left;
            }else if(root.val < p.val){
                root = root.right;
            }else{
                break;
            }
        }
        return succ;
    }
    private static TreeNode findMinChild(TreeNode p){
        while(p.left != null){
            p = p.left;
        }
        return p;
    }


    public static TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
          while(p.left != null){
              return findMinRightChild(p.left);
          }
          TreeNode predecessor = null;
          while(root != null){
              if(root.val > p.val){
                  root = root.left;
              }else if(root.val < p.val){
                  predecessor = root;
                  root = root.right;
              }else{
                  break;
              }
          }
          return predecessor;
    }

    private static TreeNode findMinRightChild(TreeNode p){
          while(p.right != null){
              p = p.right;
          }
          return p;
    }


    public int numIslands(char[][] grid) {
          if(grid == null || grid.length == 0){
              return 0;
          }
          int noOfIslands = 0;
          for(int i=0; i<grid.length;  i++){
              for(int j=0; j<grid[0].length;  j++){
                    if(grid[i][j] == '1'){
                        noOfIslands += dfs(grid, i, j);
                    }
              }
          }
          return noOfIslands;
    }
    private int dfs(char[][] grid, int i, int j){
          if(i<0 || i>=grid.length || j<0 || j>=grid.length || grid[i][j] == '0'){
              return 0;
          }
          grid[i][j] = '0';
          dfs(grid, i+1, j);
          dfs(grid, i-1, j);
          dfs(grid, i, j+1);
          dfs(grid, i, j-1);
         return 1;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        inorderPredecessor(root, root.right);
    }
  }


