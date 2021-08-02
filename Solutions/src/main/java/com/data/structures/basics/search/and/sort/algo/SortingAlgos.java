package com.data.structures.basics.search.and.sort.algo;

import java.util.ArrayList;
import java.util.Arrays;

public class SortingAlgos {

    /** Video ref: https://www.youtube.com/playlist?list=PL2_aWCzGMAwKedT2KfDMB9YA5DgASZb3U **/
    /** Find min in the array and swap **/
    public static int[] selectionSort(int[] arr, int n){
        for(int i=0; i<n-1; i++){
            int minIndex = i;
            for(int j= i+1; j<n; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    /** Comparision happens between a[i-1] and a[i] and it goes for n-1 passes **/
    public static int[] bubbleSort(int[] arr, int n){
        for(int j=1; j<n; j++) {
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }


    /** Just like cards placement, you create hole in between to fit the relevant card
     * best case o(n) in case of sorted array and in case of worst case it is o(n^2)
     * **/
    public static int[] insertionSort(int[] arr, int n){
        for(int i=1; i<n; i++){
            int value = arr[i];
            int hole = i;
            while(hole > 0 && arr[hole -1] > value){
                arr[hole] = arr[hole-1];
                hole = hole - 1;
            }
            arr[hole] = value;
        }
        return arr;
    }

    /**
     * Video ref: https://www.youtube.com/watch?v=TzeBrDU-JaY&list=PL2_aWCzGMAwKedT2KfDMB9YA5DgASZb3U&index=5
     * @param arr
     * @return
     */
   public static void mergeSort(int[] arr){
       int n = arr.length;
       if (n < 2) {
           return;
       }
       int mid = n/2;
       int left[] = new int[mid];
       int right[] = new int[n - mid];
       for(int i=0; i<mid; i++){
           left[i] = arr[i];
       }
       for(int j=mid; j<n; j++){
           right[j - mid] = arr[j];
       }
       mergeSort(left);
       mergeSort(right);
       merge(arr, left, right);
    }
    private static void merge(int[] arr, int[] left, int[] right){
       int i=0, j=0, k=0;
       int nLeft = left.length;
       int nRight = right.length;
       while(i < nLeft && j < nRight){
           if(left[i] <= right[j]){
               arr[k] = left[i];
               i++;
           }else if(left[i] > right[j]){
               arr[k] = right[j];
               j++;
           }
           k++;
       }

       while(i < nLeft){
           arr[k] = left[i];
           i++;
           k++;
       }
        while(j < nRight){
            arr[k] = right[j];
            j++;
            k++;
        }
    }


    public static void quickSort(int[] arr, int start, int end){
       if(start < end){
           int pIndex = partition(arr, start, end);
           quickSort(arr, start, pIndex-1);
           quickSort(arr, pIndex + 1, end);
       }
    }
    private static int partition(int[] arr, int start, int end){
       int pIndex = start;
       int pivot = arr[end];
       for(int i=start; i<end; i++){
           if(pivot >= arr[i]){
               int temp = arr[pIndex];
               arr[pIndex] = arr[i];
               arr[i] = temp;
               pIndex++;
           }
       }
       int temp =  arr[pIndex];
        arr[pIndex] = arr[end];
        arr[end] = temp;
        return pIndex;
    }

    /** https://www.youtube.com/watch?v=8KGqMZ6BHFY **/
    public static void radixSort(int[] arr) {
        final int RADIX = 10;
        ArrayList<Integer> bucketsArr[] = new ArrayList[RADIX];
        for (int i = 0; i < bucketsArr.length; i++) {
            bucketsArr[i] = new ArrayList<>();
        }
        boolean maxDigitLengthReached = false;
        int temp = -1, placeValue = 1;
        while (!maxDigitLengthReached) {
            maxDigitLengthReached = true;
            for (Integer item : arr) {
                temp = item / placeValue;
                bucketsArr[temp % RADIX].add(item);
                if (maxDigitLengthReached && temp > 0) {
                    maxDigitLengthReached = false;
                }
            }
            int a = 0;
            for (int b = 0; b < RADIX; b++) {
                for (Integer i : bucketsArr[b]) {
                    arr[a++] = i;
                }
                bucketsArr[b].clear();
            }
            placeValue = placeValue * RADIX;
        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void main(String[] args) {
        int arr[] = {2,5,3,1,7,6};
        radixSort(arr);
    }
}


