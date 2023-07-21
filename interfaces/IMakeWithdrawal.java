package gr.aueb.cf.bankApp.interfaces;

import gr.aueb.cf.bankApp.model.Account;

public interface IMakeWithdrawal {
    void makeWithdrawal(Account account, Double amount);
}
