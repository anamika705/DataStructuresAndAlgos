package com.data.structures;

import com.data.structures.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeDS {
    /**
     * TreeNode node = new TreeNode(1);
     *  node.setLeft(new TreeNode(2));
     *  node.setRight(new TreeNode(3));
     *  node.getLeft().setLeft(new TreeNode(4));
     *  node.getRight().setLeft(new TreeNode(5));
     *  node.getRight().setRight(new TreeNode(6));
     *  node.getRight().getRight().setLeft(new TreeNode(8));
     *  node.getRight().getLeft().setRight(new TreeNode(7));
     *  System.out.println("Max depth: " + maxDepth(node));
     */


    /**
     * Given the root of a binary tree, return its maximum depth.
     *
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     */
        public static int maxDepth(TreeNode root) {
          if(root == null){
              return 0;
          }
          int leftDepth = maxDepth(root.getLeft());
          int rightDepth = maxDepth(root.getRight());
            if (leftDepth > rightDepth)
                return (leftDepth + 1);
            else
                return (rightDepth + 1);
        }

    /**
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     *
     * A valid BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * Ref : https://www.youtube.com/watch?v=s6ATEkipzow
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min, long max){
        if(root == null) return true;
        if(root.getVal() >= max || root.getVal() <= min) return false;
        return isValidBST(root.getLeft(), min, root.getVal()) && isValidBST(root.getRight(), root.getVal(), max);
    }


    /**
     * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     *      1
     *    /   \
     *   2     2
     *  / \   / \
     * 3   4 4   3
     * Ref: https://www.youtube.com/watch?v=K7LyJTWr2yA
     */
    public static boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetric(root.getLeft(), root.getRight());
    }

    public static boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null || right == null){
            return left == right;
        }
        if(left.getVal() != right.getVal()){
            return false;
        }
        return isSymmetric(left.getLeft(), right.getRight()) && isSymmetric(left.getRight(), right.getLeft());
    }


    /**
     * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
     * Ref: https://www.youtube.com/watch?v=XZnWETlZZ14
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> mainList = new ArrayList<>();
        if(root == null){
            return mainList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> levels = new ArrayList<>();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                levels.add(node.getVal());
                if(node.getLeft() != null){
                    queue.offer(node.getLeft());
                }
                if(node.getRight() != null){
                    queue.offer(node.getRight());
                }
            }
            mainList.add(levels);
        }
        return mainList;
    }

    /**
     * Given an integer array nums where the elements are sorted in ascending order,
     * convert it to a height-balanced binary search tree.
     * A height-balanced binary tree is a binary tree in which the
     * depth of the two subtrees of every node never differs by more than one.
     * input [-10,-3,0,5,9]
     *         0
     *        / \
     *      -10  5
     *        \   \
     *        -3   9
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return formBST(0, nums.length - 1, nums);
    }
    private TreeNode formBST(int left, int right, int[] nums){
        if(left > right){
            return null;
        }
        int mid = (left+right)/2;
        TreeNode root = new TreeNode();
        root.setVal(mid);
        root.setLeft(formBST(left, mid - 1, nums));
        root.setRight(formBST(mid+1, right, nums));
        return root;
    }


    /**************************** Medium Issues Starts **************************/
    /**
     * Given the root of a binary tree, return the inorder traversal of its nodes' values.
     * @param root
     * ref video : https://www.youtube.com/watch?v=nzmtCFNae9k
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if(root == null){
            return nodes;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(stack.size() > 0 || curr != null){
            while(curr != null){
                stack.push(curr);
                curr = curr.getLeft();
            }
                curr = stack.pop();
                nodes.add(curr.getVal());
                curr = curr.getRight();
            }
        return nodes;
    }


    /**
     * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
     * ref: https://www.youtube.com/watch?v=YBw5JRZK9_g
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if(root == null){
            return output;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while(!queue.isEmpty()){
            count++;
            int size =queue.size();
            List<Integer> innerList = new ArrayList<>();
            while(size-- > 0) {
                root = queue.poll();
                innerList.add(root.getVal());
                if(root.getLeft() != null){
                    queue.add(root.getLeft());
                }
                if(root.getRight() != null){
                    queue.add(root.getRight());
                }
            }
            if(count % 2 == 0){
                Collections.reverse(innerList);
            }
            output.add(innerList);
        }
        return output;
    }


    /**
     * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
     * inorder is the inorder traversal of the same tree, construct and return the binary tree.
     * Ref: https://www.youtube.com/watch?v=FBpQEQkQ1No
     */
    HashMap<Integer, Integer> map = new HashMap<>();
    int preIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, preorder.length - 1);
    }

    private TreeNode buildTree (int[] preorder, int[] inorder, int start, int end){
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
        int index = map.get(root.getVal());
        root.setLeft(buildTree(preorder, inorder, start, index - 1));
        root.setRight(buildTree(preorder, inorder, index + 1, end));
        return root;
    }




    /**
     * You are given a perfect binary tree where all leaves are on the same level, and every parent has
     * two children. The binary tree has the following definition:
     * Populate each next pointer to point to its next right node. If there is no next right node,
     * the next pointer should be set to NULL.
     * Initially, all next pointers are set to NULL.
     * ref: https://www.youtube.com/watch?v=KX6gz1-pjg0
     */
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
                return null;
            }else if(curr == null){
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



    /**
     * Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.
     * Ref: https://www.youtube.com/watch?v=C6r1fDKAW_o
     * @param root
     * @param k
     * @return
     */
    public static int kthSmallest(TreeNode root, int k) {
        int[] nums = new int[2];
        inorder(root, nums, k);
        return nums[1];
    }
    private static void inorder(TreeNode root, int[] nums, int k){
        if(root == null){
            return;
        }
        inorder(root.getLeft(), nums, k);
        if(++nums[0] == k){
            nums[1] = root.getVal();
            return;
        }
        inorder(root.getRight(), nums, k);
    }


    /**
     * Given the root of a binary search tree and a node p in it, return the in-order successor of
     * that node in the BST. If the given node has no in-order successor in the tree, return null.
     *
     * The successor of a node p is the node with the smallest key greater than p.val.
     * Ref: https://www.youtube.com/watch?v=kdK_5rl1cVw
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        while(p.getRight() != null){
            return findMinVal(p.getRight());
        }
        TreeNode succ = null;
        while(root != null){
            if(root.getVal() > p.getVal()){
                succ = root;
                root = root.getLeft();
            }else if(root.getVal() < p.getVal()){
                root = root.getRight();
            }else{
                break;
            }
        }
        return succ;
    }
    private TreeNode findMinVal(TreeNode p){
        while(p.getLeft() != null){
            p = p.getLeft();
        }
        return p;
    }




    /***
     * Inorder predessor
     * @param p
     * @return
     */
    public static TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        while(p.getLeft() != null){
            return findMinRightChild(p.getLeft());
        }
        TreeNode predecessor = null;
        while(root != null){
            if(root.getVal() > p.getVal()){
                root = root.getLeft();
            }else if(root.getVal() < p.getVal()){
                predecessor = root;
                root = root.getRight();
            }else{
                break;
            }
        }
        return predecessor;
    }
    private static TreeNode findMinRightChild(TreeNode p){
        while(p.getRight() != null){
            p = p.getRight();
        }
        return p;
    }


    /**
     * Ref: https://www.youtube.com/watch?v=o8S2bO3pmO4
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int noOfIslands = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == '1'){
                    noOfIslands += dfs(grid, i, j);
                }

            }
        }
        return noOfIslands;
    }
    private int dfs(char[][] grid, int i, int j){
        if(i<0 || i>=grid.length || j>=grid[i].length || j<0 || grid[i][j] == '0'){
            return 0;
        }
        grid[i][j] = '0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
        return 1;
    }
}


