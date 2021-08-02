package com.data.structures;

import com.data.structures.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
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
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }
    private boolean isSymmetric(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null){
            return false;
        }
        return (left.getVal() == right.getVal()) && isSymmetric(left.getLeft(), right.getRight()) && isSymmetric(left.getRight(), right.getLeft());
    }



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
     * ref: https://www.youtube.com/watch?v=GeltTz3Z1rw
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      return formBST(0,0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode formBST(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder){
        if(preStart > preorder.length-1 || inStart > inEnd){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for(int i=inStart; i<=inEnd; i++){
            if(root.getVal() == inorder[i]){
                inIndex = i;
            }
        }
        root.setLeft(formBST(preStart+1, inStart, inIndex-1, preorder,inorder));
        root.setRight(formBST(preStart+inIndex-inStart+1, inIndex+1, inEnd, preorder,inorder));
        return root;
    }

}


