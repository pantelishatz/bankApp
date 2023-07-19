package gr.aueb.cf.bankApp.controllers;

import gr.aueb.cf.bankApp.exceptions.SsnNotValidException;
import gr.aueb.cf.bankApp.interfaces.MakeOverdraftWithdrawalInterface;
import gr.aueb.cf.bankApp.interfaces.PersistenceControllerInterface;
import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.OverdraftAccount;
import gr.aueb.cf.bankApp.model.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class MakeOverdraftWithdrawalController implements MakeOverdraftWithdrawalInterface {
    private PersistenceControllerInterface persistenceController;

    public MakeOverdraftWithdrawalController(PersistenceControllerInterface persistenceController) {
        this.persistenceController = persistenceController;
    }

    public void makeOverdraftWithdrawal(OverdraftAccount account, Double amount) {
        try {
            account.withdraw(amount, account.getHolder().getSsn() );

            // Record the transaction in a text file
            persistenceController.recordTransaction(String.valueOf(LocalDateTime.now()),"Overdraft Withdrawal", amount, account.getIban());
        } catch (SsnNotValidException e) {
            System.err.println("Overdraft withdrawal failed: " + e.getMessage());
        }
    }

}
