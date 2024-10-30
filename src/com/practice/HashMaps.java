package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class HashMaps {
    public static int majorityElement(int[] num){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int j : num) {
            if (map.containsKey(j)) {
                map.put(j, map.get(j) + 1);
            } else {
                map.put(j, 1);
            }
        }
        int max = 0;
        int ans = 0;
        for(Map.Entry<Integer,Integer> e: map.entrySet()){
            if(e.getValue()>max){
                max = e.getValue();
                ans = e.getKey();
            }
        }
        return ans;
    }
    // union of two arrays
    public static ArrayList union(int[] a, int[] b) {
        HashSet<Integer> set = new HashSet<>();
        for (int j : a) {
            set.add(j);
        }
        for (int j : b) {
            set.add(j);
        }
        ArrayList<Integer> ans = new ArrayList<>(set);
        return ans;

    }

    // intersection of two arrays
    public static ArrayList intersection(int[] a, int[] b) {
        HashSet<Integer> set = new HashSet<>();
        for (int j : a) {
            set.add(j);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int j : b) {
            if (set.contains(j)) {
                ans.add(j);
            }
        }
        return ans;
    }

    // find itineraries form tickets
    // start find
    public static String startPoint(HashMap<String,String> ticket){
        HashMap<String,String> reverse = new HashMap<>();
        for(Map.Entry<String,String> e: ticket.entrySet()){
            reverse.put(e.getValue(),e.getKey());
        }
        String start = "";
        for(Map.Entry<String,String> e: ticket.entrySet()){
            if(!reverse.containsKey(e.getKey())){
                start = e.getKey();
                break;
            }
        }
        return start;

    }

    // subarray with sum 3
    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0,1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


    public static void main(String[] args){
//        HashMap<String,Integer> map = new HashMap<>();
//
//        map.put("gfg", 10);
//        map.put("ide", 20);
//        map.put("courses", 30);
//
//        System.out.println("Size of map is:- " + map.size());
//        System.out.println(map);
//
//        // Searching for the key
//        if(map.containsKey("gfg")){
//            Integer a = map.get("gfg");
//            System.out.println("value for key" + " \"gfg\" is:- " + a);
//        }
//        map.remove("ide");
//
//        // for loop
//        for(Map.Entry<String,Integer> e: map.entrySet()){
//            System.out.println(e.getKey() + " : " + e.getValue());
//        }

//        int[] nums = {1,3,2,5,1,3,1,5,1};
//        int[] nums2 = {1,2,3,4,5};
//        System.out.println(majorityElement(nums));
//        System.out.println  (union(nums, nums2));
//        System.out.println(intersection(nums, nums2));





        HashMap<String,String> tickets = new HashMap<>();
        tickets.put("Chennai","Banglore");
        tickets.put("Bombay","Delhi");
        tickets.put("Goa","Chennai");
        tickets.put("Delhi","Goa");
        String start = startPoint(tickets);

        while (tickets.containsKey(start)){
            System.out.print(start + " -> " + tickets.get(start) + ", ");
            start = tickets.get(start);
        }
        System.out.println();
        int[] nums = {10,2,-2,-20,10};
        System.out.println(subarraySum(nums,-10));



    }
}
