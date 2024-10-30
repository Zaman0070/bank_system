package com.practice;

public class Tries {


    // word breaking problem
    public static boolean wordBreak(String key){
        if (key.isEmpty()){
            return true;
        }
        for (int i = 1; i <= key.length(); i++) {
          String first = key.substring(0,i);
          String second = key.substring(i);
            if (wordBreak(first) && wordBreak(second)){
                return true;
            }
        }
        return false;
    }

    // count unique substrings
//    public static int countUniqueSubstrings(String key){
//        Set<String> set = new HashSet<>();
//        for (int i = 0; i < key.length(); i++) {
//            for (int j = i+1; j <= key.length(); j++) {
//                set.add(key.substring(i,j));
//            }
//        }
//        return set.size();
//    }

    public static void main(String[] args){
//        System.out.println(wordBreak("ilikesamsung"));
//        System.out.println(countUniqueSubstrings("ababa"));
    }

}
