package gr.aueb.cf.bankApp.interfaces;

import gr.aueb.cf.bankApp.model.Account;

public interface IMakeDepositControllerInterface {
    void makeDeposit(Account account, Double amount);
}
