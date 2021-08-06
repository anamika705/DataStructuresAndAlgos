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


    /**
     * https://www.youtube.com/watch?v=COk73cpQbFQ&list=PL2_aWCzGMAwKedT2KfDMB9YA5DgASZb3U&index=7
     * @param arr
     * @param start
     * @param end
     */
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



    /** https://www.youtube.com/watch?v=JMlYkE8hGJM **/
    public static int[] bucketSort(int[] arr){
        int RADIX = 10;
        int passes = getDigitsOfMaxNum(arr);
        ArrayList<Integer> buckets[] = new ArrayList[RADIX];
        for(int i=0; i<buckets.length; i++){
            buckets[i] = new ArrayList<>();
        }
        int placeholder = 1;
        for(int i=0; i<passes; i++){
            for(int j=0; j<arr.length; j++){
                int bucketIndex = (arr[j]/placeholder) % 10;
                buckets[bucketIndex].add(arr[j]);
            }
            int a=0;
            for(int k=0; k<10; k++){
                for(int m=0; m < buckets[k].size(); m++){
                   arr[a++] = buckets[k].get(m);
                }
                buckets[k].clear();
            }
            placeholder = placeholder * 10;
        }
        return arr;
    }
    private static int getDigitsOfMaxNum(int[] arr){
        int max = arr[0];
        for(int i=1; i<arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return String.valueOf(arr).length();
    }


    /** Identification --- if k is given and asked for smallest/largest
     * k+smallest ---> maxHeap
     * k+largest ----> minHeap
     * Heap
     * last leaf node = n/2
     * leaf nodes range = Math.floor(n/2) + 1 ..... n
     * height of heap = log n
     * parent is at i
     * left child is at 2i
     * right child is at 2i + 1
     * Video Ref : https://www.youtube.com/watch?v=UVW0NfG_YWA
     */
    public static int[] buildMaxHeap(int[] arr){
        int size = arr.length;
        for(int i=size/2; i>0; i--){
            maxheapify(arr, size, i);
        }
        return arr;
    }
    private static void maxheapify(int[] arr, int size, int i){
        int largest = i;
        int left = 2*i;
        int right = 2*i + 1;
        if(left<=size && arr[left-1] > arr[largest-1]){
            largest = left;
        }
        if(right<=size && arr[right-1] > arr[largest-1]){
            largest = right;
        }
        if(largest != i){
            int temp = arr[i-1];
            arr[i-1] = arr[largest-1];
            arr[largest-1] = temp;
            maxheapify(arr, size, largest);
        }
    }
    public static int[] heapSortMax(int[] arr){
        int size = arr.length;
        buildMaxHeap(arr);
        for(int i=size-1; i>0; i--){
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            maxheapify(arr,i, 1);
        }
        return arr;
    }


    public static int[] buildMinHeap(int[] arr){
        int n = arr.length;
        for(int i= n/2; i>0; i--){
            minHeapify(arr, n, i);
        }
        return arr;
    }

    private static void minHeapify(int[] arr, int n, int i){
        int smallest = i;
        int left = 2*i;
        int right = 2*i + 1;
        if(left <= n && arr[left-1] < arr[smallest-1]){
            smallest = left;
        }
        if(right<=n && arr[right-1] < arr[smallest-1]){
            smallest = right;
        }
        if(smallest != i){
            int temp = arr[i-1];
            arr[i-1] = arr[smallest-1];
            arr[smallest-1] = temp;
            minHeapify(arr, n, smallest);
        }
    }
    private static int[] heapSortMin(int[] arr){
        buildMinHeap(arr);
        int k = arr.length;
        for(int i=k-1; i>0; i--){
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0]  = temp;
            minHeapify(arr, k, 1);
        }
        return arr;
    }


    public static int getNthSmallestElement(int[] arr, int k){
        buildMinHeap(arr);
        int heap_size = arr.length;
        for(int i=0; i<k-1; i++){
            if(heap_size == 0){
                return Integer.MAX_VALUE;
            }
            if(heap_size > 1){
                arr[0] = arr[heap_size - 1];
                minHeapify(arr, heap_size, 1);
            }
            heap_size--;
        }
        return arr[0];
    }

    public static int getNthLargestElement(int[] arr, int k){
        buildMaxHeap(arr);
        int heap_size = arr.length;
        for(int i=0; i<k-1; i++){
            if(heap_size == 0){
                return Integer.MIN_VALUE;
            }
            if(heap_size > 1){
                arr[0] = arr[heap_size-1];
                maxheapify(arr, heap_size, 1);
            }
            heap_size--;
        }
        return arr[0];
    }



    public static void main(String[] args) {
        int arr[] = {10, 30, 50, 20, 35, 15, 45, 55, 90, 52};
        System.out.println(getNthLargestElement(arr,6));
    }
}


