package com.data.structures;

import java.lang.String;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MathDS {

    /**
     * Given an integer n, return a string array answer (1-indexed) where:
     *
     * answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
     * answer[i] == "Fizz" if i is divisible by 3.
     * answer[i] == "Buzz" if i is divisible by 5.
     * answer[i] == i if non of the above conditions are true.
     */
    public List<String> fizzBuzz(int n) {
        List<String> chars = new ArrayList<>();
        for(int i=1; i<n+1; i++){
                if (i % 3 == 0 && i % 5 == 0) {
                    chars.add("FizzBuzz");
                } else if (i % 3 == 0) {
                    chars.add("Fizz");
                } else if (i % 5 == 0) {
                    chars.add("Buzz");
                } else {
                    chars.add(String.valueOf(i));
                }
        }
        return chars;
    }


    /**
     * Count the number of prime numbers less than a non-negative number, n.
     */
    public int countPrimes(int n) {
        if(n == 0 || n == 1){
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        for(int i=2; i<n; i++){
            isPrime[i] = true;
        }
        for(int i=2; i*i<n; i++){
                if(!isPrime[i]){
                    continue;
                }else{
                    for(int j = i*i; j<n; j+=i){
                        isPrime[j] = false;
                    }
                }
        }
        int count = 0;
        for(int i = 2; i<n; i++){
            if(isPrime[i]){
                count++;
            }
        }
        return count;
    }


    /**
     * Given an integer n, return true if it is a power of three. Otherwise, return false.
     *
     * An integer n is a power of three, if there exists an integer x such that n == 3x.
     */
    public boolean isPowerOfThree(int n) {
        if(n == 1){
            return true;
        }
        if(n == 0 || n % 3 != 0){
            return false;
        }
        int x = n/3;
        if( x != 1 && x % 3 != 0){
            return false;
        }
        if (x == 3) {
            return true;
        }
        return isPowerOfThree(x);
    }


    /**
     * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
     *
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given a roman numeral, convert it to an integer.
     */
    public int romanToInt(String s) {
        TreeMap<String, Integer> map =  getMapOfRomanNumbers();
        int size = s.length();
        int k = 0;
        if(size == 1){
            return map.get(String.valueOf(s.charAt(0)));
        }
        int requiredStr = 0;
        for(int i=1; i<size; i++){
            if(s.charAt(k) == 'I' && s.charAt(i) == 'V'){
                requiredStr += 4;
                k = i+1;
            }
            else if(s.charAt(k) == 'I' && s.charAt(i) == 'X'){
                requiredStr += 9;
                k = i+1;
            }
            else if(s.charAt(k) == 'X' && s.charAt(i) == 'L'){
                requiredStr += 40;
                k = i+1;
            }
            else if(s.charAt(k) == 'X' && s.charAt(i) == 'C'){
                requiredStr += 90;
                k = i+1;
            }
            else if(s.charAt(k) == 'C' && s.charAt(i) == 'D'){
                requiredStr += 400;
                k = i+1;
            }
            else if(s.charAt(k) == 'C' && s.charAt(i) == 'M'){
                requiredStr += 900;
                k = i+1;
            }else{

                if(i != k) {
                    requiredStr += map.get(String.valueOf(s.charAt(k)));
                    k++;
                }
                if(k == size - 1){
                    requiredStr += map.get(String.valueOf(s.charAt(k)));
                }
            }
        }
        return requiredStr;
    }
    private TreeMap<String, Integer> getMapOfRomanNumbers(){
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        return map;
    }
}
