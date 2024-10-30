package com.practice;

public class QueueY {

    //Queue implementation using linked list

    static class Node{
        static int data;
        static Node next;
        Node(int data){
            Node.data = data;
            next = null;
        }

    }

    static class QueueLinkList{
        static Node head = null;
        static Node tail = null;


        static boolean isEmpty(){
            return head == null & tail == null;
        }

        public static void add(int data){
            Node newNode = new Node(data);
            if(isEmpty()){
                head = tail = newNode;
            }else{
                Node.next = newNode;
                tail = newNode;
            }
        }

        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int temp = Node.data;
            if(head==tail){
                head = tail = null;
            }else{
                head = Node.next;
            }
            return temp;
        }

        public static int peak(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            System.out.println(Node.data);
            return Node.data;
        }
    }




    static class Queue{




       static int[] arr;
      static int size;
      static int rear = -1;
      static int front = -1;

        Queue(int n,int size){
            arr = new int[n];
           Queue.size = size;
        }
        public static boolean isEmpty(){
            return rear == -1 && front == -1;
        }

        public static boolean isFull(){
            return (rear+1) % size == front;
        }

        public static void add(int data){
            if(isFull()){
                System.out.println("Queue is full");
                return;
            }
            if(front==-1){
                front = 0;
            }
            rear = (rear+1) % size;
            arr[rear] = data;
        }

        public static int remove(int data){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int temp = arr[front];
            if(rear==front){
                front = rear = -1;
            }else{
                front = (front+1) % size;

            }
            return temp;
        }

        public static int peak(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return-1;
            }
            System.out.println(arr[0]);
            return arr[front];
        }
    }
    public static void main(String[] args){
        Queue queue = new Queue(5,5);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.peak();
        queue.remove(1);
        queue.peak();
    }
}
