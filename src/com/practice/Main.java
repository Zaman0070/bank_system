// This is the main class of the project
package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {
    public static void main(String[] args)throws ClassNotFoundException {


        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "Saman@12345";

        /////********* UPLOAD FILE HANDLING   *********/////

        String image_path = "/Users/apple/Desktop/WhatsApp Image 2024-08-27 at 7.05.59 AM.jpg";
        String folder_path = "/Users/apple/Desktop/web";
        String query = "SELECT * FROM image_tb WHERE image_id = (?)";

        /////********* TRANSACTION HANDLING   *********/////
        String withdrawQuery = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
        String depositQuery = "UPDATE account SET balance = balance + ? WHERE account_number = ?";

        /////********* BATCH PROCESSING *********/////
        String batchQuery = "INSERT INTO employees (name,job_title,salary) VALUES (?, ?, ?)";


        try
            (Connection connection = DriverManager.getConnection(url, username, password)){
                System.out.println("Connection successful");


                /////********* BATCH PROCESSING *********/////
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement(batchQuery);
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Enter name, job title and salary");
                    String name = scanner.next();
                    String job_title = scanner.next();
                    double salary = scanner.nextDouble();
                    ps.setString(1, name);
                    ps.setString(2, job_title);
                    ps.setDouble(3, salary);
                    ps.addBatch();
                    System.out.println("Do you want to add more records? Y/N");
                    String ans = scanner.next();
                    if (ans.equalsIgnoreCase("n")) {
                        break;
                    }
                }
                int[] affectedRows = ps.executeBatch();
                connection.commit();
                System.out.println("Batch processing successful");

                /////********* TRANSACTION HANDLING   *********/////
//             try {
//                 connection.setAutoCommit(false);
//                 PreparedStatement ps1 = connection.prepareStatement(withdrawQuery);
//                 PreparedStatement ps2 = connection.prepareStatement(depositQuery);
//                 ps1.setDouble(1,500.00);
//                 ps1.setString(2,"1");
//                 ps2.setDouble(1,500.00);
//                 ps2.setString(2,"2");
//                    int affectedRows1 = ps1.executeUpdate();
//                    int affectedRows2 = ps2.executeUpdate();
//                    if(affectedRows1>0 && affectedRows2>0){
//                        connection.commit();
//                        System.out.println("Transaction successful");
//                    }else {
//                        connection.rollback();
//                        System.out.println("Transaction failed");
//                    }
//             } catch (SQLException e){
//                 connection.rollback();
//                 System.out.println("Transaction failed");
//                }



                ///////////////********* DOWNLOAD IMAGE FROM DATABASE *********/////
//                PreparedStatement ps = connection.prepareStatement(query);
//                ps.setInt(1,1);
//                ResultSet rs = ps.executeQuery();
//                if(rs.next()){
//                    String image_paths = folder_path+"extracted_image.jpg";
//                    byte[] image = rs.getBytes("image_data");
//                    OutputStream os = new FileOutputStream(image_paths);
//                    os.write(image);
//
//                }else {
//                    System.out.println("Image download failed");
//                }

                ////////********* UPLOAD IMAGE TO DATABASE *********/////
//            FileInputStream fis = new FileInputStream(image_path);
//            byte[] image = new byte[fis.available()];
//            fis.read(image);
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setBytes(1,image);
//          int affectedRows =   ps.executeUpdate();
//            if (affectedRows>0) {
//                System.out.println("Image uploaded successfully");
//            }else {
//                System.out.println("Image upload failed");
//            }


            }catch (SQLException e){
                System.out.println("Connection failed");
                System.out.println(e.getMessage());
            }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }




// Recursive function to solve Tower of Hanoi puzzle
    public static void towerOfHanoi(
            int n,String scr,String helper,String dest
    ){
        if(n==1){
            System.out.println("Move disk 1 from "+scr+" to "+dest);
            return;
        }
        towerOfHanoi(n-1,scr,dest,helper);
        System.out.println("Move disk "+n+" from "+scr+" to "+dest);
        towerOfHanoi(n-1,helper,scr,dest);
    }

    // print a string in reverse
    public static void reverseString(String str){
        if(str.isEmpty()){
            return;
        }
        reverseString(str.substring(1));
        System.out.print(str.charAt(0));
    }

    // find the 1st and last occurrence of an element in string
    public static void findOccurrence(String str,char ch){
        int first = str.indexOf(ch);
        int last = str.lastIndexOf(ch);
        System.out.println("First occurrence of "+ch+" is at index "+first);
        System.out.println("Last occurrence of "+ch+" is at index "+last);
    }


    // check if an array is sorted(strictly increasing)
    public static boolean isSorted(int[] arr,int i){
        if(i==arr.length-1){
            return true;
        }
        if(arr[i]>=arr[i+1]){
            return false;
        }
        return isSorted(arr,i+1);
    }

    // move all x to the end of the array
    public static void moveToEnd(int[] arr,int i,int x){
        if(i==arr.length){
            return;
        }
        if(arr[i]==x){
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]!=x){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    break;
                }
            }
        }
        moveToEnd(arr,i+1,x);
    }

    // remove duplicate characters from a string

    public static String removeDuplicates(String str){
        if(str.isEmpty() || str.length()==1){
            return str;
        }
        if(str.charAt(0)==str.charAt(1)){
            return removeDuplicates(str.substring(1));
        }
        return str.charAt(0)+removeDuplicates(str.substring(1));
    }


    ///
    public static String[] keyPad = {".","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public static void printKeyPadCombinations(String str,String ans){
        if(str.isEmpty()){
            System.out.println(ans);
            return;
        }
        char ch = str.charAt(0);
        String code = keyPad[ch-'0'];
        String rest = str.substring(1);
        for(int i=0;i<code.length();i++){
            printKeyPadCombinations(rest,ans+code.charAt(i));
        }
    }

    // print all permutations of a string

    public static void printPermutations(String str,String ans){
        if(str.isEmpty()){
            System.out.println(ans);
            return;
        }
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            String rest = str.substring(0,i)+str.substring(i+1);
            printPermutations(rest,ans+ch);
        }
    }

    // count total path maze to move from (0,0) to (n,m)

    public static int countPath(int n,int m){
        if(n==1 || m==1){
            return 1;
        }
        return countPath(n-1,m)+countPath(n,m-1);
    }


    public static  int callGuest(int n){
        if(n<=1){
            return 1;
        }
        return n+callGuest(n-1);

    }


   public static void findSubset(int m,ArrayList<Integer> arr){
        if(m==0){
            printSubset(arr);
            return;
        }
        findSubset(m-1,arr);
        arr.add(m);
        findSubset(m-1,arr);
        arr.removeLast();
   }

   public static void printSubset(ArrayList<Integer> arr){
       for(Integer integer : arr) {
           System.out.print(integer + " ");
       }
         System.out.println();
   }





}