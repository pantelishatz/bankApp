package gr.aueb.cf.bankApp.interfaces;

import gr.aueb.cf.bankApp.model.Account;

public interface IMakeWithdrawalInterface {
    void makeWithdrawal(Account account, Double amount);
}
