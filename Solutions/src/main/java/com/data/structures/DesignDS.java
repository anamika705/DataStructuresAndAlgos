package com.data.structures;

import java.util.Random;
import java.util.Stack;

public class DesignDS {
    /**
     * Fisher-Yates Algorithm
     */
    class Solution {
        Random rand = new Random();
        private int[] array;
        private int[] original;

        public Solution(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        private int randRange(int min, int max) {
            return rand.nextInt(max - min) + min;
        }

        private void swapAt(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return original;
        }

        public int[] shuffle() {
            for (int i = 0; i < array.length; i++) {
                swapAt(i, randRange(i, array.length));
            }
            return array;
        }
    }



    /**
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     *
     * Implement the MinStack class:
     *
     * MinStack() initializes the stack object.
     * void push(val) pushes the element val onto the stack.
     * void pop() removes the element on the top of the stack.
     * int top() gets the top element of the stack.
     * int getMin() retrieves the minimum element in the stack.
     */
    class MinStack {
        private Stack<int[]> minStack = new Stack<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int val) {
            if (minStack.isEmpty()) {
                minStack.push(new int[]{val, val});
                return;
            }
            int current_min = minStack.peek()[1];
            minStack.push(new int[]{val, Math.min(val, current_min)});

        }

        public void pop() {
            minStack.pop();
        }

        public int top() {
            return minStack.peek()[0];
        }

        public int getMin() {
            return minStack.peek()[1];
        }
    }

}

