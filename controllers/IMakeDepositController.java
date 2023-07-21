package gr.aueb.cf.bankApp.controllers;

import gr.aueb.cf.bankApp.exceptions.InsufficientAmountException;
import gr.aueb.cf.bankApp.interfaces.IMakeDepositControllerInterface;
import gr.aueb.cf.bankApp.interfaces.IPersistenceControllerInterface;
import gr.aueb.cf.bankApp.model.Account;

import java.time.LocalDateTime;


public class IMakeDepositController implements IMakeDepositControllerInterface {

    private IPersistenceControllerInterface persistenceController;

    public IMakeDepositController(IPersistenceControllerInterface persistenceController) {
        this.persistenceController = persistenceController;
    }

    public void makeDeposit(Account account, Double amount) {
        try {
            account.deposit(amount);
            // Record the transaction in a text file
            persistenceController.recordTransaction(String.valueOf(LocalDateTime.now()), "Deposit", amount, account.getIban());
        } catch (InsufficientAmountException e) {
            System.err.println("Deposit failed: " + e.getMessage());
        }
    }
}
