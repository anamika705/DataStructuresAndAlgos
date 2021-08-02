package com.data.structures;

import java.util.Map;
import java.util.TreeMap;

public class SortingAndSearchingDS {

    /**
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
     * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that
     * should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0){
            System.out.println(nums1);
            return;
        }
        if(m == 0){
            for(int i=0; i<nums2.length; i++){
                nums1[i] = nums2[i];
            }
            System.out.println(nums1);
            return;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int j=0; j<m; j++) {
            map.put(nums1[j], map.getOrDefault(nums1[j], 0) + 1);

        }
        for (int f=0; f<n; f++) {
            map.put(nums2[f], map.getOrDefault(nums2[f], 0) + 1);
        }
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int currVal = entry.getValue();
            for(int k = 0; k<currVal; k++){
                nums1[count] = entry.getKey();
                count++;
            }
        }
        System.out.println(nums1);
    }

    public int[] mergeApproach2(int[] nums1, int m, int[] nums2, int n) {
        // Set p1 and p2 to point to the end of their respective arrays.
        int p1 = m - 1;
        int p2 = n - 1;

        // And move p backwards through the array, each time writing
        // the smallest value pointed at by p1 or p2.
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
        return nums1;
    }


    /**
     * You are a product manager and currently leading a team to develop a new product.
     * Unfortunately, the latest version of your product fails the quality check.
     * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
     * You are given an API bool isBadVersion(version) which returns whether version is bad.
     * Implement a function to find the first bad version. You should minimize the number of calls to the API.
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(isBadVersion(mid)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
    private boolean isBadVersion(int num){
        return false;
    }
}
