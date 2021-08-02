package com.data.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Others {

    /**
     * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
     */
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            n= n & (n-1);
            count++;
        }
        return count;
    }


    /**
     * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     * Given two integers x and y, return the Hamming distance between them.
     */
    public int hammingDistance(int x, int y) {
        int count = 0;
        int n = x ^ y;
        while(n != 0){
            n= n & (n-1);
            count++;
        }
        return count;
    }


    /**
     * Reverse bits of a given 32 bits unsigned integer.
     */
    public int reverseBits(int n) {
        int res = 0;
        for(int i=0; i<32; i++){
            int lsb = n & 1;
            int reverseLSB = lsb << (31 - i);
            res = res  | reverseLSB;
            n = n >> 1;
        }
        return res;
    }


    /**
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     */
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> requiredResult = new ArrayList<>();
        for(int i=0; i<numRows; i++){
            List<Integer> subResult = new ArrayList<>();
            if(i == 0){
                subResult.add(1);
            }
            else if(i == 1){
                subResult.add(1);
                subResult.add(1);
            }else {
                for (int k = 0; k < i+1; k++) {
                    if (k == 0 || k == i) {
                        subResult.add(1);
                    } else {
                        subResult.add(requiredResult.get(i - 1).get(k - 1) + requiredResult.get(i - 1).get(k));
                    }
                }

            }

            requiredResult.add(subResult);
        }
        return requiredResult;
    }


    /**
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     */
    public boolean isValid(String s) {
        Stack<Character> chars = new Stack<>();
        if(s.length() % 2 != 0){
            return false;
        }
        for(char c : s.toCharArray()){
            if(c == '(' || c == '{' || c == '['){
                chars.push(c);
            }
            else if(c == ')' && !chars.isEmpty() && chars.peek() == '('){
                chars.pop();
            }
            else if(c == ']' && !chars.isEmpty() && chars.peek() == '['){
                chars.pop();
            }
            else if(c == '}' && !chars.isEmpty() && chars.peek() == '{'){
                chars.pop();
            }else{
                return false;
            }
        }
        return chars.isEmpty();
    }


    /**
     * Given an array nums containing n distinct numbers in the range [0, n],
     * return the only number in the range that is missing from the array.
     */
    public int missingNumber(int[] nums) {
        int count = 0;
        int expectedCount = 0;
        int size = nums.length;
        for(int i=0; i<size; i++){
            count += nums[i];
            expectedCount += i;
        }
        expectedCount += size;
        return expectedCount - count;
    }
}
