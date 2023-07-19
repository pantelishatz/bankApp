package gr.aueb.cf.bankApp.controllers;

import gr.aueb.cf.bankApp.exceptions.InsufficientBalanceException;
import gr.aueb.cf.bankApp.exceptions.SsnNotValidException;
import gr.aueb.cf.bankApp.interfaces.MakeWithdrawalInterface;
import gr.aueb.cf.bankApp.interfaces.PersistenceControllerInterface;
import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class MakeWithdrawalController implements MakeWithdrawalInterface {
    private PersistenceControllerInterface persistenceController;

    public MakeWithdrawalController(PersistenceControllerInterface persistenceController) {
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
