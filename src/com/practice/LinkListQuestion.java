package com.practice;

import java.util.LinkedList;

public class LinkListQuestion {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val,ListNode next) {this. val = val;this.next=next; }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {
                return null;
            }
            int size = 0;
            ListNode current = head;
            while (current != null) {
                size++;
                current = current.next;
            }
            if(n == size){
                return head.next;
            }
            int indexToRemove = size - n;
            ListNode prev = head;
            int i = 1;
            while (i < indexToRemove ) {
                prev = prev.next;
                i++;
            }
            prev.next = prev.next.next;
            return head;
        }

        // isPalindrome
        public boolean isPalindrome(ListNode head) {
            if (head == null||head.next==null) {
                return true;
            }
            ListNode middle = findMiddle(head);
            ListNode secondHalf = reverse(middle.next);

            ListNode firstHalf = head;
            while (secondHalf != null) {
                if (firstHalf.val != secondHalf.val) {
                    return false;
                }
                firstHalf = firstHalf.next;
                secondHalf = secondHalf.next;
            }
            return true;
        }

        public ListNode findMiddle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        public ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode current = head;
            while (current != null) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;
        }


        // hasCycle
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }

        // detectCycle
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    ListNode slow2 = head;
                    while (slow2 != slow) {
                        slow = slow.next;
                        slow2 = slow2.next;
                    }
                    return slow;
                }
            }
            return null;
        }

        // reverseIterative
        public ListNode reverseIterative(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode prev = null;
            ListNode current = head;
            while (current != null) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;
        }
    }

   static Node head ;

  static   class Node{
        String data;
        Node next;
        Node(String data){
            this.data = data;
            next = null;
        }
    }

    // addFirst
    static void addFirst(String data){
        Node newNode = new Node(data);
       if(head==null) {
           head = newNode;
       }
       newNode.next = head;
       head = newNode;
    }

    // addLast
    static void addLast(String data){
        Node newNode = new Node(data);
        if(head==null){
            head = newNode;
        }
            Node current = head;
            while (current.next!=null){
                current = current.next;
            }
            current.next = newNode;

    }

    // printList
    static void printList(){
        if(head==null){
            System.out.println("list is empty");
            return;
        }
        Node current = head;
        while (current!=null){
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("a");
        list.addFirst("is");
        System.out.println(list);

        list.addLast("this");
        list.add("linked");
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);


    }

}
