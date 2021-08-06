package com.cracking.coding.interview.string.and.arrays.searching.and.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class problems {
    /**
     * You are given two sorted arrays A and B, where A has a large enough buffer at the end to hold B.
     * Write a method to merge B into A in sorted order
     * @param A
     * @param B
     * @param lastA
     * @param lastB
     * @return
     */
    static int[] sortedMerge(int[] A, int[] B, int lastA, int lastB){
        int indexA = lastA - 1;
        int indexB = lastB - 1;
        int mergedIndex = lastA + lastB - 1;
        while(indexB >= 0){
            if(indexA >=0 && A[indexA] > B[indexB]){
                A[mergedIndex] = A[indexA];
                indexA--;
            }else{
                A[mergedIndex] = B[indexB];
                indexB--;
            }
            mergedIndex--;
        }
        return A;
    }



    public static void main(String[] args) {
        String[] A = {"acre","boot", "toob", "race", "care", };

    }
}

