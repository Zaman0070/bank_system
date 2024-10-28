package com.bankingmanagementsystem;

public class Queries {
    // Base query components
    private static final String INSERT = "INSERT INTO ";
    private static final String SELECT = "SELECT * FROM ";
    private static final String UPDATE = "UPDATE ";
    private static final String DELETE = "DELETE FROM ";

    // User-related queries
    public static final String USER_REGISTER = INSERT + "users (name, email, password) VALUES (?, ?, ?)";
    public static final String USER_LOGIN = SELECT + "users WHERE email = ? AND password = ?";
    public static final String USER_BY_EMAIL = SELECT + "users WHERE email = ?";

    // Account-related queries
    public static final String ACCOUNT_OPEN = INSERT + "accounts (account_no, name, email, balance, pin) VALUES (?, ?, ?, ?, ?)";
    public static final String ACCOUNT_BY_EMAIL = SELECT + "accounts WHERE email = ?";
    public static final String ACCOUNT_BY_NO = SELECT + "accounts WHERE account_no = ?";
    public static final String GENERATE_ACCOUNT_NO = "SELECT MAX(account_no) FROM accounts";
    public static final String SELECT_BALANCE_BY_NO_AND_PIN = "SELECT balance FROM accounts WHERE account_no = ? AND pin = ?";
    public static final String CREDIT_AMOUNT = UPDATE + "accounts SET balance = balance + ? WHERE account_no = ?";
    public static final String DEBIT_AMOUNT = UPDATE + "accounts SET balance = balance - ? WHERE account_no = ?";





}