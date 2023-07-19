package gr.aueb.cf.bankApp.controllers;

import gr.aueb.cf.bankApp.exceptions.InsufficientAmountException;
import gr.aueb.cf.bankApp.interfaces.MakeDepositControllerInterface;
import gr.aueb.cf.bankApp.interfaces.PersistenceControllerInterface;
import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;


public class MakeDepositController implements MakeDepositControllerInterface {

    private PersistenceControllerInterface persistenceController;

    public MakeDepositController(PersistenceControllerInterface persistenceController) {
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
