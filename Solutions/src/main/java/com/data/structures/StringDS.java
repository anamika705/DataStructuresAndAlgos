package com.data.structures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class StringDS {
    /**
     * Write a function that reverses a string. The input string is given as an array of characters s.
     */
    public void reverseString(char[] strArray) {
        int j = strArray.length - 1;
        int i = 0;
        while(i < j){
            char temp = strArray[i];
            strArray[i] = strArray[j];
            strArray[j] = temp;
            i++;
            j--;
        }
        System.out.println(Arrays.toString(strArray));
    }

    /**
     * Given a signed 32-bit integer x, return x with its digits reversed.
     * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
     */
    public int reverseInteger(int num){
        int rev_num = 0;
        int prev_rev_num=0;
        while(num != 0)
        {
            int curr_digit = num % 10;
            rev_num = rev_num * 10 + curr_digit;
            System.out.println(curr_digit);
            System.out.println(rev_num);
            if((rev_num - curr_digit)/10 != prev_rev_num){
                return 0;
            }
            prev_rev_num = rev_num;
            num = num / 10;

        }
        return rev_num;
    }


    /**
     * Given a string s, return the first non-repeating character in it and return its index.
     * If it does not exist, return -1.
     * Time Complexity : O(n)
     * Space Complexity O(26) ~ O(1)
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i), 1);
            }
        }
        for(int i=0; i < s.length(); i++){
            if(map.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }


    /**
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     */
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> mapS = fillFrequencyMap(s);
        HashMap<Character, Integer> mapT = fillFrequencyMap(t);
        return mapS.equals(mapT); //Time complexity O(n) for Hashmap equals
    }
    private HashMap<Character, Integer> fillFrequencyMap(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        return map;
    }


    /**
     * Given a string s, determine if it is a palindrome, considering only alphanumeric
     * characters and ignoring cases.
     */
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != (s.charAt(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    /**
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
     *
     * The algorithm for myAtoi(string s) is as follows:
     *
     * Read in and ignore any leading whitespace.
     * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * Read in next the characters until the next non-digit charcter or the end of the input is reached. The rest of the string is ignored.
     * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * Return the integer as the final result.
     */
    public int myAtoi(String s) {
        s = s.trim(); // step 1: remove white space
        if(s == null || s.length() == 0) return 0;
        int sign = 1, ans = 0;

        for(int i = 0; i < s.length();i++){
            char c = s.charAt(i);
            if(i == 0 && (c =='-' || c == '+')){ // step 2: check negative sign
                sign = c=='-'? -1:1;
                continue;
            }

            int temp = c - '0';
            if(temp < 0 || temp > 9) break; // step 3
            if((Integer.MAX_VALUE-temp)/10 < ans){ // step 5
                return sign == 1? Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            ans = ans*10+temp; // step 4
        }
        return ans*sign;
    }


    /**
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack
     */
    public int strStr(String haystack, String needle) {
        int size1 = haystack.length();
        int size2 = needle.length();
        int j = 0;
        int i;
        if(size2 == 0){
            return 0;
        }
        for(i = 0; i<size1; i++){
            if(haystack.charAt(i) == needle.charAt(j)){
                j++;
            }else if(j < size2){
                i = i - j;
                j = 0;
            }else{
                break;
            }
        }
        if(j == size2){
            return i-j;
        }
        return -1;
    }


    /**
     * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
     *
     * countAndSay(1) = "1"
     * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string
     *  1.     1
     *  2.     11
     *  3.     21
     *  4.     1211
     */
    public String countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        if(n == 2){
            return "11";
        }
        String requiredStr = "11";
        for(int i=3; i<=n; i++){
            requiredStr = requiredStr + "$";
            String bufferString = "";
            int count = 1;
            for(int j=1; j<requiredStr.length(); j++){
                if(requiredStr.charAt(j) != requiredStr.charAt(j-1)){
                    bufferString += String.valueOf(count) +  requiredStr.charAt(j-1);
                    count = 1;
                }else{
                    count++;
                }
            }
            requiredStr = bufferString;
        }
        return requiredStr;
    }


    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        String prefix = "";
        for(int i = 0; i<strs[0].length(); i++){
            Character currentChar = strs[0].charAt(i);
            for(int j = 1; j< strs.length; j++){
                if(i >= strs[j].length() || strs[j].charAt(i) != currentChar){
                    return prefix;
                }
            }
            prefix += currentChar;
        }
        return prefix;
    }



    /**
     * Given a string s, find the length of the longest substring without repeating characters.
     * Ref: https://www.youtube.com/watch?v=4i6-9IzQHwo
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int max_length = 1;
        int j=0, i=0;
        HashSet<Character> char_set = new HashSet();
        while(i<s.length()){
            while(char_set.contains(s.charAt(i))){
                char_set.remove(s.charAt(j));
                j++;
            }
            char_set.add(s.charAt(i));
            max_length = Math.max(max_length, i-j+1);
            i++;
        }
        return max_length;
    }

    /**
     * Given a string s, return the longest palindromic substring in s.
     * https://www.youtube.com/watch?v=y2BD4MJqV20
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        int start = 0;
        int end = 0;
        for(int i=0; i<s.length(); i++){
            int len1 = expandFromMiddle(s, i, i);
            int len2 = expandFromMiddle(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > end - start){
                start = i - ((len-1)/2);
                end = i + (len/2);
            }
        }
        return s.substring(start, end+1);
    }

    private int expandFromMiddle(String s, int left, int right){
        if(s == null || left > right){
            return 0;
        }
        while(left >=0 && right<s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }


    /**
     * Min wondow substring
     * Ref: https://www.youtube.com/watch?v=U1q16AFcjKs
     */
    public String minSubstring(String s1, String s2){
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0){
            return "";
        }
        int i=0, j=0, left=0, right=s1.length()-1, minLength = s1.length();
        boolean found = true;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int k=0; k<s2.length(); k++){
            map.put(s2.charAt(k), map.getOrDefault(s2.charAt(k), 0) + 1);
        }
        int count = map.size();
        while(j < s1.length()){
            char endChar = s1.charAt(j++);
            if(map.containsKey(endChar)){
                map.put(endChar, map.get(endChar) - 1);
                if(map.get(endChar) == 0){
                    count--;
                }
            }
            if(count > 0){
                continue;
            }
            while(count == 0){
                char startChar = s1.charAt(i++);
                if(map.containsKey(startChar)){
                    map.put(startChar, map.get(startChar) + 1);
                    if(map.get(startChar) > 0){
                        count++;
                    }
                }
            }
            if((j-i) < minLength){
                left = i;
                right = j;
                minLength = j-i;
            }
        }
        return s1.substring(left-1, right);
    }

    public int minRotations(int input, int unlock_code){
        int count = 0;
        int input_digit, code_digit;
        while(input>0 || unlock_code>0){
            input_digit = input % 10;
            code_digit = unlock_code % 10;
            count += Math.min(Math.abs(input_digit - code_digit), Math.abs(10 - (input_digit - code_digit)));
            input/=10;
            unlock_code/=10;
        }
        return count;
    }


}
