package gr.aueb.cf.bankApp.controllers;

import gr.aueb.cf.bankApp.exceptions.InsufficientBalanceException;
import gr.aueb.cf.bankApp.exceptions.SsnNotValidException;
import gr.aueb.cf.bankApp.interfaces.IMakeWithdrawal;
import gr.aueb.cf.bankApp.interfaces.IPersistenceController;
import gr.aueb.cf.bankApp.model.Account;

import java.time.LocalDateTime;

public class MakeWithdrawalController implements IMakeWithdrawal {
    private IPersistenceController persistenceController;

    public MakeWithdrawalController(IPersistenceController persistenceController) {
        this.persistenceController = persistenceController;
    }

    public void makeWithdrawal(Account account, Double amount) {
        try {
            account.withdraw(amount, account.getHolder().getSsn());

            // Record the transaction in a text file
            persistenceController.recordTransaction(String.valueOf(LocalDateTime.now()), "Withdrawal", amount, account.getIban());
        } catch (InsufficientBalanceException | SsnNotValidException e) {
            System.err.println("Withdrawal failed: " + e.getMessage());
        }
    }
}
