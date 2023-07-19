package gr.aueb.cf.bankApp.controllers;

import gr.aueb.cf.bankApp.interfaces.DisplayBalanceInterface;
import gr.aueb.cf.bankApp.interfaces.PersistenceControllerInterface;
import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.OverdraftAccount;
import gr.aueb.cf.bankApp.model.User;

import java.util.Map;

public class DisplayBalanceController implements DisplayBalanceInterface {

    public void displayBalance(User user, Map<User, Account> accountsMap, Map<User, OverdraftAccount> overdraftAccountsMap) {
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
}
