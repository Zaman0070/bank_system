package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTracking {

    public static void main(String[] args) {
        // Call solveNQueens for n = 4 and print the result
        List<List<String>> solutions = solveNQueens(4);
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();  // Print an empty line between solutions
        }
    }

    public static void printPermutations(String str,String ans){
        if(str.isEmpty()){
            System.out.println(ans);
            return;
        }
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            String ros = str.substring(0,i)+str.substring(i+1);
            printPermutations(ros,ans+ch);
        }
    }

    // Function to solve the N-Queens problem
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize the board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        helper(board, res, 0);
        return res;
    }

    // Helper function to perform backtracking
    public static void helper(char[][] board, List<List<String>> allBoards, int col) {
        // Base case: if we've placed queens in all columns
        if (col == board.length) {
            List<String> list = new ArrayList<>();
            for (char[] chars : board) {
                list.add(new String(chars));
            }
            allBoards.add(list);
            return;
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';  // Place queen
                helper(board, allBoards, col + 1);  // Recur to place the next queen
                board[row][col] = '.';  // Backtrack: remove queen
            }
        }
    }

    // Function to check if it's safe to place a queen at board[row][col]
    public static boolean isSafe(char[][] board, int row, int col) {
        // Check the current row on the left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }

        // Check upper diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check lower diagonal on the left side
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;  // Safe to place the queen
    }
}
