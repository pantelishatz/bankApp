package gr.aueb.cf.bankApp.controllers;

import gr.aueb.cf.bankApp.exceptions.SsnNotValidException;
import gr.aueb.cf.bankApp.interfaces.IMakeOverdraftWithdrawalInterface;
import gr.aueb.cf.bankApp.interfaces.IPersistenceControllerInterface;
import gr.aueb.cf.bankApp.model.OverdraftAccount;

import java.time.LocalDateTime;

public class IMakeOverdraftWithdrawalController implements IMakeOverdraftWithdrawalInterface {
    private IPersistenceControllerInterface persistenceController;

    public IMakeOverdraftWithdrawalController(IPersistenceControllerInterface persistenceController) {
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
