package com.revature.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class MenuPrinter {

    private BufferedOutputStream out;

    public MenuPrinter(BufferedOutputStream out) {
        this.out = out;
    }

    public void printInputMessage() throws IOException {
        out.write("Enter the Number of the option to select it (or enter 'q' to quit out): ".getBytes());
        out.flush();
    }

    public void printMessage(String msg) throws IOException {
        out.write(msg.getBytes());
        out.flush();
    }

    public void printPreAuthenticated() throws IOException {
        out.write("Welcome to Revature Banking Application\n".getBytes());
        out.write("---------------------------------------\n".getBytes());
        out.write("1. Login\n".getBytes());
        out.write("2. Register\n".getBytes());
        out.flush();
    }

    public void printLoggedinMenu(String classname) throws IOException {
        out.write("Main Menu\n".getBytes());
        out.write("---------------------------------------\n".getBytes());
        out.write(("You have logged in as a(n): " + classname + "\n").getBytes());
        out.write("1. View Accounts\n".getBytes());
        out.write("2. View Personal Information\n".getBytes());
        out.flush();
    }

    public void printAccountMenuCustomer() throws IOException {
        out.write("---------------------------------------\n".getBytes());
        out.write("Account Management:\n".getBytes());
        out.write("1. Apply for an Account\n".getBytes());
        out.write("2. View pending accounts\n".getBytes());
        out.write("3. Add user to joint account/Make account joint\n".getBytes());
        out.write("4. Withdraw funds from an account\n".getBytes());
        out.write("5. Deposit funds to an account\n".getBytes());
        out.write("6. Transfer funds between your accounts\n".getBytes());
        out.flush();
    }

    public void printAccountMenuEmployee() throws IOException {
        out.write("Account Management\n".getBytes());
        out.write("---------------------------------------\n".getBytes());
        out.write("1. View Pending Account Applications\n".getBytes());
        out.write("2. View Customers\n".getBytes());
        out.flush();
    }

    public void printAccountMenuAdmin() throws IOException {
        out.write("Account Management\n".getBytes());
        out.write("---------------------------------------\n".getBytes());
        out.write("1. View Pending Account Applications\n".getBytes());
        out.write("2. View Approved Accounts\n".getBytes());
        out.write("3. View Customers\n".getBytes());
        out.flush();
    }

    public void printApprovalMenu() throws IOException {
        out.write("---------------------------------------\n".getBytes());
        out.write("Pending Accounts:\n".getBytes());
        out.write("1. Approve Account\n".getBytes());
        out.write("2. Deny Account\n".getBytes());
        out.flush();
    }

    public void printCustomerMenu() throws IOException {
        out.write("---------------------------------------\n".getBytes());
        out.write("Customers:\n".getBytes());
        out.write("1. View Customer Details\n".getBytes());
        out.flush();
    }

    public void printAppovedAccountMenu() throws IOException {
        out.write("---------------------------------------\n".getBytes());
        out.write("Approved Accounts:\n".getBytes());
        out.write("1. Cancel Account\n".getBytes());
        out.write("2. Withdraw funds from an account\n".getBytes());
        out.write("3. Deposit funds to an account\n".getBytes());
        out.write("4. Transfer funds between accounts\n".getBytes());
        out.flush();
    }
    
}
