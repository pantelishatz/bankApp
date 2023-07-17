package gr.aueb.cf.bankApp.actions;

import gr.aueb.cf.bankApp.exceptions.InsufficientAmountException;
import gr.aueb.cf.bankApp.exceptions.InsufficientBalanceException;
import gr.aueb.cf.bankApp.exceptions.SsnNotValidException;
import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.OverdraftAccount;
import gr.aueb.cf.bankApp.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class BankActions {

    public static void makeDeposit(User user, Scanner scanner, Map<User, Account> accountsMap) {
        Account account = accountsMap.get(user);
        if (account == null) {
            System.out.println("No account found for the selected user.");
            return;
        }

        System.out.println("Enter the amount to deposit:");
        double amount = scanner.nextDouble();
        try {
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: " + account.getAccountBalance());

            // Record the transaction in a text file
            recordTransaction("Deposit", amount, account.getIban());
        } catch (InsufficientAmountException e) {
            System.err.println("Deposit failed: " + e.getMessage());
        }
    }

    public static void makeWithdrawal(User user, Scanner scanner, Map<User, Account> accountsMap) {
        Account account = accountsMap.get(user);
        if (account == null) {
            System.out.println("No account found for the selected user.");
            return;
        }

        System.out.println("Enter the amount to withdraw:");
        double amount = scanner.nextDouble();
        System.out.println("Enter the SSN:");
        String ssn = scanner.next();
        try {
            account.withdraw(amount, ssn);
            System.out.println("Withdrawal successful. New balance: " + account.getAccountBalance());

            // Record the transaction in a text file
            recordTransaction("Withdrawal", amount, account.getIban());
        } catch (InsufficientBalanceException | SsnNotValidException e) {
            System.err.println("Withdrawal failed: " + e.getMessage());
        }
    }

    public static void makeOverdraftWithdrawal(User user, Scanner scanner, Map<User, OverdraftAccount> overdraftAccountsMap) {
        OverdraftAccount overdraftAccount = overdraftAccountsMap.get(user);
        if (overdraftAccount == null) {
            System.out.println("No overdraft account found for the selected user.");
            return;
        }

        System.out.println("Enter the amount to withdraw (including overdraft):");
        double amount = scanner.nextDouble();
        System.out.println("Enter the SSN:");
        String ssn = scanner.next();
        try {
            overdraftAccount.withdraw(amount, ssn);
            System.out.println("Overdraft withdrawal successful. New balance: " + overdraftAccount.getAccountBalance());

            // Record the transaction in a text file
            recordTransaction("Overdraft Withdrawal", amount, overdraftAccount.getIban());
        } catch (SsnNotValidException e) {
            System.err.println("Overdraft withdrawal failed: " + e.getMessage());
        }
    }

    public static void displayBalance(User user, Map<User, Account> accountsMap, Map<User, OverdraftAccount> overdraftAccountsMap) {
        System.out.println("=== Balance ===");
        Account account = accountsMap.get(user);
        if (account != null) {
            System.out.println("Regular Account Balance: " + account.getAccountBalance());
        }
        OverdraftAccount overdraftAccount = overdraftAccountsMap.get(user);
        if (overdraftAccount != null) {
            System.out.println("Overdraft Account Balance: " + overdraftAccount.getAccountBalance());
        }
    }

    private static void recordTransaction(String transactionType, double amount, String iban) {
        try {
            FileWriter writer = new FileWriter("transactions.txt", true);
            String transactionRecord = LocalDateTime.now() + " - " + transactionType + ": Amount=" + amount + ", IBAN=" + iban;
            writer.write(transactionRecord + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing transaction to file: " + e.getMessage());
        }
    }
}