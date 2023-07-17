package gr.aueb.cf.bankApp;

import gr.aueb.cf.bankApp.actions.BankActions;
import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.OverdraftAccount;
import gr.aueb.cf.bankApp.model.User;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

    public class Main {

        public static void main(String[] args) {
            // Create users
            User user1 = new User("John", "Doe", "123456789");
            User user2 = new User("Jane", "Smith", "987654321");
            User user3 = new User("Nick", "Elf", "456754321");

            // Create individual accounts
            Account account1 = new Account("GR1234567890", user1, 1000.0);
            Account account2 = new Account("GR9876543210", user2, 500.0);
            Account account3 = new Account("GR3333333333", user3, 2000.0);

            // Create overdraft accounts
            OverdraftAccount overdraftAccount1 = new OverdraftAccount("GR1111111111", user1, 1500.0);
            OverdraftAccount overdraftAccount2 = new OverdraftAccount("GR2222222222", user2, 2000.0);
            OverdraftAccount overdraftAccount3 = new OverdraftAccount("GR2222222222", user3, 2000.0);

            // Create a map to store accounts per user
            Map<User, Account> accountsMap = new HashMap<>();
            accountsMap.put(user1, account1);
            accountsMap.put(user2, account2);
            accountsMap.put(user3, account3);

            // Create a map to store overdraft accounts per user
            Map<User, OverdraftAccount> overdraftAccountsMap = new HashMap<>();
            overdraftAccountsMap.put(user1, overdraftAccount1);
            overdraftAccountsMap.put(user2, overdraftAccount2);
            overdraftAccountsMap.put(user3, overdraftAccount3);

            // Scanner for user input
            Scanner scanner = new Scanner(System.in);

            // Main menu loop
            boolean exit = false;
            while (!exit) {
                System.out.println("=== Bank Application Menu ===");
                System.out.println("1. Select User");
                System.out.println("0. Exit");

                try {
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            User selectedUser = selectUser(user1, user2, user3, scanner);
                            if (selectedUser != null) {
                                performActions(selectedUser, scanner, accountsMap, overdraftAccountsMap);
                            }
                            break;
                        case 0:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Clear the input buffer
                }
            }

            // Close the scanner
            scanner.close();
        }

        private static User selectUser(User user1, User user2, User user3, Scanner scanner) {
            System.out.println("=== Select User ===");
            System.out.println("1. User 1: " + user1.getFirstname() + " " + user1.getLastname());
            System.out.println("2. User 2: " + user2.getFirstname() + " " + user2.getLastname());
            System.out.println("3. User 3: " + user3.getFirstname() + " " + user3.getLastname());
            System.out.println("0. Back");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    return user1;
                case 2:
                    return user2;
                case 3:
                    return user3;
                case 0:
                    // Do nothing, go back to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            return null;
        }

        private static void performActions(User user, Scanner scanner, Map<User, Account> accountsMap, Map<User, OverdraftAccount> overdraftAccountsMap) {
            boolean exit = false;
            while (!exit) {
                System.out.println("=== Actions for " + user.getFirstname() + " " + user.getLastname() + " ===");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Withdraw with Overdraft");
                System.out.println("4. Display Balance");
                System.out.println("0. Back");

                try {
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            BankActions.makeDeposit(user, scanner, accountsMap);
                            break;
                        case 2:
                            BankActions.makeWithdrawal(user, scanner, accountsMap);
                            break;
                        case 3:
                            BankActions.makeOverdraftWithdrawal(user, scanner, overdraftAccountsMap);
                            break;
                        case 4:
                            BankActions.displayBalance(user, accountsMap, overdraftAccountsMap);
                            break;
                        case 0:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Clear the input buffer
                }
            }
        }
    }