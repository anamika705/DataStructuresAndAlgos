package com.cracking.coding.interview.string.and.arrays;

import java.util.HashMap;
import java.util.Map;

public class ArraysAndStrings {
    public static void main(String[] args) {
        int[][] arr = {{1,1,1,1}, {1,0,1,1}, {1,1,0,1}, {0,0,0,1}};
        System.out.println(stringRotation("waterbottle", "erbottlewat"));

    }

    public static boolean isUniqueCharInString(String s){
        int k = 0;
        if(s == ""){
            return true;
        }
        Character pointer = s.charAt(k);
        for(int i=1; i<s.length(); i++){
            if(pointer == s.charAt(i)){
                return false;
            }
            if(i == s.length() - 1){
                k++;
                pointer = s.charAt(k);
                i = k;
            }
        }
        return true;
    }

    public static boolean isPermutation(String s1, String s2){
        HashMap<Character, Integer> map1 = fillMap(s1);
        HashMap<Character, Integer> map2 = fillMap(s2);
        if(s1.length() != s2.length()){
            return false;
        }
        for(Map.Entry<Character, Integer> item : map1.entrySet()){
            if( map2.get(item.getKey()) == null || item.getValue() > map2.get(item.getKey())){
                return false;
            }
        }
        return true;
    }

    private static HashMap<Character, Integer> fillMap(String s){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i<s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
        }
        return map;
    }

    public static String urLify(char[] str, int trueLength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        index = trueLength + spaceCount * 2;
        if (trueLength < str.length) str[trueLength] = '\0';
        for (i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
        return str.toString();
    }



   public static boolean isPalindromePermutation(String s){
       HashMap<Character, Integer> map1 = fillMap(s.replaceAll(" ", "").toLowerCase());
       int count = 0;
       for(Map.Entry<Character, Integer> item : map1.entrySet()){
           if(s.length() % 2 == 0 && item.getValue() % 2 != 0){
               return false;
           }
           if(s.length() % 2 != 0){
               if(item.getValue() % 2 != 0){
                   count++;
               }
           }
       }
       if(count > 1){
           return false;
       }
       return true;
   }

   public static boolean isOneWay(String s1, String s2) {
       HashMap<Character, Integer> s1Map = fillMap(s1);
       HashMap<Character, Integer> s2Map = fillMap(s2);
       //check for replacement
       if (s1.length() == s2.length() || s1.length() > s2.length()) {
           int count = 0;
            for(int i=0; i<s1.length(); i++){
                if(s2Map.get(s1.charAt(i)) == null){
                    count++;
                    if(count > 1){
                        return false;
                    }
                }
            }
       }
       //check for insertion
       if (s1.length() < s2.length()) {
           int count = 0;
           for(int i=0; i<s2.length(); i++){
               if(s1Map.get(s2.charAt(i)) == null){
                   count++;
                   if(count > 1){
                       return false;
                   }
               }
           }

       }
       return true;
   }

   public static String compression(String s){
        StringBuilder newString = new StringBuilder();
        int count = 1;
        for(int i=0; i<s.length(); i++){
            if(i == 0 && count == 1){
                newString.append(s.charAt(i));
            }
            if(i > 0 && s.charAt(i-1) == s.charAt(i)){
                count++;
            }else if (i > 0 && s.charAt(i-1) != s.charAt(i)){
                newString.append(count);
                count = 1;
                newString.append(s.charAt(i));
            }
        }
        newString.append(count);
        return newString.toString();
   }

   public static int[][] rotateMatrixBy90Clockwise(int[][] matrix){
       int rows = matrix.length;
       int cols = matrix[0].length;
       //Take transpose first
       for(int i=0; i<rows; i++){
           for(int j=i; j<cols; j++){
               int temp = matrix[i][j];
               matrix[i][j] = matrix[j][i];
               matrix[j][i] = temp;
           }
       }
       // interchange rows
       for(int i=0; i<rows; i++){
           int left = 0;
           int right = cols - 1;
           while(left < right){
               int temp = matrix[i][left];
               matrix[i][left] = matrix[i][right];
               matrix[i][right] = temp;
               left++;
               right--;
           }

       }
       return matrix;
   }

   public static int[][] rotateMatrixBy90AntiClockwise(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        //Take transpose first
        for(int i=0; i<rows; i++){
            for(int j=i; j<cols; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //interchange cols
        for(int j=0; j<cols; j++){
            int left = 0;
            int right = rows - 1;
            while(left < right){
                int temp = matrix[left][j];
                matrix[left][j] = matrix[right][j];
                matrix[right][j] = temp;
                left++;
                right--;
            }
        }
        return matrix;
   }

    public static int[][] zeroMatrix(int[][] matrix){
        int col0 = 1;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int i=0; i<rows; i++){
            if(matrix[i][0] == 0){
                col0 = 0;
            }
            for(int j=1; j<cols; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = rows-1; i>=0; i--){
            for(int j = cols-1; j>=1; j--){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
            if(col0 == 0){
                matrix[i][0] = 0;
            }
        }
        return matrix;
    }

    public static boolean stringRotation(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        String s3 = s1 + s1;
        if(!s3.contains(s2)){
            return false;
        }
        return true;
    }
}
