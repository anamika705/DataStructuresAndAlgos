package com.data.structures;

public class DynamicProgrammingDS {
    public int climbStairs(int n) {
        if(n==1){
            return 1;
        }
    /*  int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];  // new step is sum of n-1 step and then n-2 step
        }
        return dp[n];
     */
        int oneStep = 1;
        int twoStep = 2;
        for(int i = 3; i<=n; i++){
            int temp = twoStep;
            twoStep =  twoStep + oneStep;
            oneStep = temp;
        }
        return twoStep;
    }


    public int maxProfit(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<prices.length; i++){
            if(prices[i] < min){
                min = prices[i];
            }else{
                max = Math.max(max, prices[i] - min);
            }
        }
        return max;
    }


    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     * Ref video: https://www.youtube.com/watch?v=VMtyGnNcdPw
     */
    public int maxSubArray(int[] nums) {
        int size = nums.length;
        if(size == 0){
            return 0;
        }
        int curr_sum = nums[0];
        int best_sum = nums[0];
        for(int i=1; i<nums.length; i++){
            if(curr_sum + nums[i] > nums[i]){
                curr_sum += nums[i];
            }else{
                curr_sum = nums[i];
            }
            best_sum= Math.max(best_sum, curr_sum);
        }
        return best_sum;
    }


    /**
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them
     * is that adjacent houses have security systems connected and it will automatically contact the police
     * if two adjacent houses were broken into on the same night.
     *
     * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
     */
    public int rob(int[] nums) {
        int size = nums.length;
        if(size == 0){
            return 0;
        }
        if(size == 1){
            return nums[0];
        }
        int[] dp = new int[size];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<nums.length; i++){
            dp[i] = Math.max(dp[i-1], nums[i]+dp[i-2]);
        }
        return dp[size-1];
    }
}
