package com.practice;

public class TimeComplexity {

    public static void  main(String[] args){
        int n = 2;
//        System.out.println(fib(n));
//        int[] arr = {1,2,3,4,5,6,7,8,9};
//        System.out.println(binarySearch(arr, 5));
//        mergeSort(arr, 0, arr.length - 1);
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        int[] nums = {1, 3, 4, 2, 2};
//        System.out.println(findDuplicate(nums));
//        int[] arr1 = {4, 10, 3, 5, 1};
//        for (int i : arr1) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }

    // Big O Notation Time Complexity: O(2^n) and Space Complexity: O(n)
    public static int fib(int n) {
        if (n <= 1) return n;
        return fib(n-1) + fib(n-2);
    }



    // Big O Notation Time Complexity: O(log n) and Space Complexity: O(1)
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }


    // Big O Notation Time Complexity: O(n log n) and Space Complexity: O(n)
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }


    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int i = 0; i < n2; i++) R[i] = arr[mid + 1 + i];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }


    // Finding Duplicates in an Array
    // Big O Notation Time Complexity: O(n) and Space Complexity: O(1)
    public static int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // Heap Operations
    // Big O Notation Time Complexity: O(log n) and Space Complexity: O(log n)

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }


    // What is the time and space complexity of the dynamic programming solution to the

    // Longest Common Subsequence problem?
    // Big O Notation Time Complexity: O(m*n) and Space Complexity: O(m*n)

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i-1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j-1);
                if (c1 == c2) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }

}
