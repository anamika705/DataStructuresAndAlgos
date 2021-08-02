package com.data.structures;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ArraysDS {
    /**
     * Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * @return
     */
    public void removeDuplicatesMethod(int[] nums) {
        int i = 0;
        for(int j = i+1; j<nums.length; j++){
            if(nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        System.out.println("Length of new array: " + (i+1));
        System.out.println("Array after removing duplicates: ");
        for(int k = 0; k < i+1; k++){
            System.out.print(" " + nums[k]);
        }
    }


    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     */
    public int maxProfit(int[] prices) {
        int size = prices.length;
        int k = 1;
        int sum = 0;
        for(int i = 0; i<size; i++){
            if(k != size) {
                int diff = (prices[k] - prices[i]);
                if(diff > 0){
                    sum += diff;
                }
                k++;
            }
        }
        return sum;
    }


    /**
     * Given an array, rotate the array to the right by k steps, where k is non-negative.
     */
    public void rotate(int[] nums, int k) {
        int size = nums.length;
        k = k % size;
        reverseArray(nums, 0, size-k-1);
        reverseArray(nums, size-k, size-1);
        reverseArray(nums, 0, size-1);
        System.out.println(nums);
    }
    private int[] reverseArray(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
        return nums;
    }


    /**
     * Given an integer array nums, return true if any value appears at least twice in the array,
     * and return false if every element is distinct.
     */
    public boolean containsDuplicate(int[] nums) {
        int size = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<size; i++){
            if(map.containsKey(nums[i])){
                return true;
            }else{
               map.put(nums[i], 0);
            }
        }
        return false;
    }


    /**
     * Given a non-empty array of integers nums, every element appears twice except for one.
     * Find that single one.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     */
    public int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }


    /**
     * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result
     * must appear as many times as it shows in both arrays and you may return the result in any order.
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int n : nums1) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        int k = 0;
        for (int n : nums2) {
            int cnt = m.getOrDefault(n, 0);
            if (cnt > 0) {
                nums1[k++] = n;
                m.put(n, cnt - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }


    /**
     * Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.
     * The digits are stored such that the most significant digit is at the head of the list,
     * and each element in the array contains a single digit.
     * You may assume the integer does not contain any leading zero, except the number 0 itself
     */
    public int[] plusOne(int[] digits) {
        int size = digits.length;
        int carry = 0;
        if(digits[size-1] % 10 + 1 == 10){
           for(int i= size-1; i>=0; i--){
               if(digits[i] != 9){
                   digits[i] =  digits[i] + carry;
                   carry = 0;
               }else{
                   if(i == size - 1) {
                       digits[i] = 0;
                       carry = 1;
                   }else{
                       digits[i] = digits[i] + carry == 10 ? 0 : 9;
                       carry = digits[i] == 0 ? 1 : 0;
                   }
               }
           }
           if(digits[0] == 0){
               int[] newArr = new int[size + 1];
               newArr[0] = 1;
               for(int j=0; j<digits.length; j++){
                   newArr[j+1] = digits[j];
               }
               return newArr;
           }

        }else{
            digits[size-1] = digits[size-1] % 10 + 1;
        }
        return digits;
    }

    /**
     * Given an integer array nums, move all 0's to the end of it while maintaining
     * the relative order of the non-zero elements.
     */
    public void moveZeroes(int[] nums) {
        int size = nums.length;
        int j=1;
        for(int i=0; i<size; i++){
            if(j<size && nums[i] == 0){
                if(nums[i] != nums[j]){
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    j++;
                }else if(nums[i] == nums[j]){
                    j++;
                    i=i-1;
                }
            }else{
                j = i+1;
            }
        }
        System.out.println(nums);
    }


    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     */
    public int[] twoSum(int[] nums, int target) {
        int j=0;
        int[] requiredList = new int[2];
        for(int i=1; i<nums.length; i++){
            if(nums[i] + nums[j] != target && i == nums.length - 1){
                j++;
                i = j+1;
                if( i == nums.length - 1){
                    i = i - 1;
                }else{
                    i = j;
                }
            }else if(nums[i] + nums[j] == target){
                requiredList[0] = j;
                requiredList[1] = i;
                return requiredList;
            }
        }
        return requiredList;
    }


    /**
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     */
    public boolean isValidSudoku(char[][] board) {
            HashSet<String> vals = new HashSet<>();
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    char current_char = board[i][j];
                    if(current_char != '.'){
                        if(!vals.add(current_char + "is in row for index " + i) ||
                                !vals.add(current_char + "is in col for index " + j) ||
                                !vals.add(current_char + "is in sub box for " + i/3 + "-" + j/3)){
                            return false;
                        }
                    }
                }
            }
            return true;
    }




    /**
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
     * DO NOT allocate another 2D matrix and do the rotation.
     */
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }
    public void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }
    public void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }




    /**************************  Medium Problems Starts  *****************************/
    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
     * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * Notice that the solution set must not contain duplicate triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        List<List<Integer>> mainArray = new ArrayList<>();
        for(int i=0; i< size-2; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i-1])){
            int left = i+1;
            int right = size -1;
            int sum = - nums[i];
            while(left < right){
                if(sum < nums[right] + nums[left]){
                    right--;
                }else if(sum > nums[right] + nums[left]){
                    left++;
                }else if (sum == nums[right] + nums[left]){
                    mainArray.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                }
              }
           }
        }
        return mainArray;
    }


    /** Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
     * Steps:
     * 1. Check first row and first column, if they are 0, make status variable first_row = true and first_col=true
     * 2. Check rest of matrix and if 0 appears then mark outer elements as 0
     * 3. Based upon outer elements mark respective rows and cols as zero
     * 4. If first_row = true & first_col is true then make first and first col as 0
     * videp Ref: https://www.youtube.com/watch?v=BnCJaHiSodg
     */
    public void setZeroes1(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean first_row = false;
        boolean first_col = false;
        //check if first col has any zero
        for(int i=0; i<rows; i++){
           if(matrix[i][0] == 0){
               first_col = true;
           }
        }
        //check if first row has any zero
        for(int j=0; j<cols; j++){
            if(matrix[0][j] == 0){
                first_row = true;
            }
        }

        // Check if any inner elements are zero then set corresponding outer element as 0
        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // zerofy the marked rows and columns if outer elements are 0
        for(int i=1; i<rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < cols; j++) {
                     matrix[i][j] = 0;
                }
            }
        }

        for(int j=1; j<cols; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < rows; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        //Mark first row as 0 if first_row flag is true
        if(first_row) {
            for (int j = 0; j < cols; j++) {
               matrix[0][j] = 0;
            }
        }

        //Mark first col as 0 if first_col flag is true
        if(first_col) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }

        System.out.println(matrix);
    }


    
    /**
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order
     * Video Ref: https://www.youtube.com/watch?v=NNBQik4phMI
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> mainArr = new ArrayList<>();
        HashMap<HashMap<Character,Integer>, List<String>> outerMap = new HashMap<>();
        for(String item : strs){
            HashMap<Character,Integer> innerMap = new HashMap<>();
            for(int j=0; j<item.length(); j++){
                innerMap.put(item.charAt(j), innerMap.getOrDefault(item.charAt(j),0) + 1);

            }
            if(outerMap.containsKey(innerMap) == false){
                List<String> list = new ArrayList<>();
                list.add(item);
                outerMap.put(innerMap, list);
            }else{
                List<String> list = outerMap.get(innerMap);
                list.add(item);
            }
        }
        for(List<String> list : outerMap.values()){
            mainArr.add(list);
        }
        return mainArr;
    }

}
