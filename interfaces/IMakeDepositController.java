package gr.aueb.cf.bankApp.interfaces;

import gr.aueb.cf.bankApp.model.Account;
@FunctionalInterface
public interface IMakeDepositController {
    void makeDeposit(Account account, Double amount);
}
