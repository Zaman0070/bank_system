package com.practice;

import java.util.ArrayList;
import java.util.Stack;

public class StackClass {
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            next = null;
        }
    }
    static class Stacks{
         static Node head;

        public static boolean isEmpty(){
            return head == null;
        }

        public static void push(int data){
            Node newNode = new Node(data);
            if(isEmpty()){
                head = newNode;
            }else{
                newNode.next = head;
                head = newNode;
            }
        }

        public static int pop(){
            if(isEmpty()){
                System.out.println("Stack is empty");
                return -1;
            }
            int temp = head.data;
            head = head.next;
            return temp;
        }

        public static int peek(){
            if(isEmpty()){
                System.out.println("Stack is empty");
                return -1;
            }
            return head.data;
        }



        //// Stack using ArrayList
        static ArrayList<Integer> stack = new ArrayList<>();

        static boolean isEmptyList(){
            return stack.isEmpty();
        }

        static void pushList(int data){
            stack.add(data);
        }

        static int popList(){
            if(isEmptyList()){
                System.out.println("Stack is empty");
                return -1;
            }
            return stack.removeLast();
        }

        static int peekList(){
            if(isEmptyList()){
                System.out.println("Stack is empty");
                return -1;
            }
            return stack.getLast();
        }





    }
    public static void main(String[] args) {
//        Stack stack = new Stack();
        // Stack implementation with java framework
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        pushAtTheBottom(stack,4);
        reverseStack(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.peek());
            stack.pop();
        }
    }

    public static void pushAtTheBottom(Stack<Integer> stack,int data){
        if(stack.isEmpty()){
            stack.push(data);
            return;
        }
        int top = stack.pop();
        pushAtTheBottom(stack,data);
        stack.push(top);
    }

    public static void reverseStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int top = stack.pop();
        reverseStack(stack);
        pushAtTheBottom(stack,top);
    }
}
