package gr.aueb.cf.bankApp.interfaces;

import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.OverdraftAccount;
import gr.aueb.cf.bankApp.model.User;

import java.util.Map;

public interface DisplayBalanceInterface {
    void displayBalance(User user, Map<User, Account> accountsMap, Map<User, OverdraftAccount> overdraftAccountsMap);
}
