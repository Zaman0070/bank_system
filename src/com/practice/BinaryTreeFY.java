package com.practice;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeFY {

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
    static class BinaryTree{
        static int  index = -1;
        public static Node buildTree(int node[]){
            index++;
            if(node[index]==-1){
                return null;
            }
            Node newNode = new Node(node[index]);
            newNode.left = buildTree(node);
            newNode.right = buildTree(node);

            return newNode;
        }
    }

    public static void preOrder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root){
        if (root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    public static void postOrder(Node root){
        if(root==null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }

    public static void levelOrder(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()){
            Node temp = queue.poll();
            if(temp==null){
                System.out.println();
                if(!queue.isEmpty()){
                    queue.add(null);
                }
            }else{
                System.out.print(temp.data+" ");
                if(temp.left!=null){
                    queue.add(temp.left);
                }
                if(temp.right!=null){
                    queue.add(temp.right);
                }
            }
        }
    }


    public static int countNodes(Node root){
        if(root==null){
            return 0;
        }
        return 1+countNodes(root.left)+countNodes(root.right);
    }

    public static int sumNodes(Node root){
        if(root==null){
            return 0;
        }
        return root.data+sumNodes(root.left)+sumNodes(root.right);
    }

    public static int height(Node root){
        if(root==null){
            return 0;
        }
        return 1+Math.max(height(root.left),height(root.right));
    }

    public static int diameter(Node root){
        if(root==null){
            return 0;
        }
        int lheight = height(root.left);
        int rheight = height(root.right);
        int ldiameter = diameter(root.left);
        int rdiameter = diameter(root.right);
        return Math.max(lheight+rheight+1,Math.max(ldiameter,rdiameter));
    }

    //// find diameter in best approach

    static class TreeInfo{
        int diameter;
        int height;
        TreeInfo(int diameter,int height){
            this.diameter = diameter;
            this.height = height;
        }
    }

    public static TreeInfo diameterBest(Node root){
        if(root==null){
            return new TreeInfo(0,0);
        }
        TreeInfo left = diameterBest(root.left);
        TreeInfo right = diameterBest(root.right);
        int height = Math.max(left.height,right.height)+1;
        int diameter = Math.max(left.height+right.height+1,Math.max(left.diameter,right.diameter));
        return new TreeInfo(diameter,height);
    }



    public static boolean isSubTree(Node root,Node subRoot){
        if(root==null){
            return false;
        }
        if(subRoot==null){
            return true;
        }
        if(isIdentical(root,subRoot)){
            return true;
        }
        return isSubTree(root.left,subRoot) || isSubTree(root.right,subRoot);
    }

    public static boolean isIdentical(Node root,Node subRoot){
        if(root==null && subRoot==null){
            return true;
        }
        if(root==null || subRoot==null){
            return false;
        }
        return root.data==subRoot.data && isIdentical(root.left,subRoot.left) && isIdentical(root.right,subRoot.right);
    }


    // sum of node of kth level
    public static int sumOfKthLevel(Node root,int k){
        if(root==null){
            return 0;
        }
        if(k==0){
            return root.data;
        }
        return sumOfKthLevel(root.left,k-1)+sumOfKthLevel(root.right,k-1);
    }



    public static void main(String[] args) {
       int node[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
       BinaryTree tree = new BinaryTree();
       Node root = tree.buildTree(node);
         System.out.println(root.data);
//         preOrder(root);
//         inOrder(root);
//        postOrder(root);
//        levelOrder(root);
//        System.out.println(countNodes(root));
//        System.out.println(sumNodes(root));
//        System.out.println(height(root));
//        System.out.println(diameter(root));
//        TreeInfo info = diameterBest(root);
//        System.out.println(info.diameter);
        System.out.println(isSubTree(root,root));
//        System.out.println(sumOfKthLevel(root,2));
    }

}
