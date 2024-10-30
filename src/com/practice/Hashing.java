package com.practice;

import java.util.HashSet;
import java.util.Iterator;

public class Hashing {
    public static void main(String[] args){
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        // search
//        if(set.contains(1)){
//            System.out.println("1 is present");
//        }
        // remove
        set.remove(1);
        if(!set.contains(1)) {
            System.out.println("1 is not present");
        }

        System.out.println(set.size()+" is the size of the set");
        System.out.println(set);
        // Iterating over the set
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
