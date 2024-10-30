package com.practice;

public class BST {

    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static Node insert(Node root, int data){
        if(root==null){
            return new Node(data);
        }
        if(data<root.data){
            root.left = insert(root.left,data);
        }else{
            root.right = insert(root.right,data);
        }
        return root;
    }

    public static Node inOrder(Node root){
        if (root==null){
            return null;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
        return root;
    }

    public static Node deleteNode(Node root,int val){
        if(root.data>val){
            root.left = deleteNode(root.left,val);
        }
        if (root.data<val){
            root.right = deleteNode(root.right,val);
        }
        else {
            if (root.left==null && root.right==null){
                return null;
            }
            if (root.left==null){
                return root.right;
            }
            if (root.right==null){
                return root.left;
            }

            Node succ = getSuccessor(root);
            root.data = succ.data;
            root.right = deleteNode(root.right,succ.data);
        }
        return root;
    }

    public static Node getSuccessor(Node root){
        Node curr = root.right;
        while (curr!=null && curr.left!=null){
            curr = curr.left;
        }
        return curr;
    }

    public static void printInRange(Node root,int k1,int k2){
        if(root==null){
            return;
        }
        if(root.data>k1){
            printInRange(root.left,k1,k2);
        }
        if(root.data>=k1 && root.data<=k2){
            System.out.print(root.data+" ");
        }
        if(root.data<k2){
            printInRange(root.right,k1,k2);
        }
    }
    // root to leaf path print
    public static void printRootToLeaf(Node root,String path){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            System.out.println(path+root.data);
        }
        printRootToLeaf(root.left,path+root.data+" ");
        printRootToLeaf(root.right,path+root.data+" ");
    }


    public static void main(String[] args) {
        int[] value = {8,5,3,1,4,6,10,11,14};
        Node root = null;
        for (int i : value) {
            root = insert(root,i);
        }
        System.out.println();
        printInRange(root,5,10);
        printRootToLeaf(root,"");
    }
}
