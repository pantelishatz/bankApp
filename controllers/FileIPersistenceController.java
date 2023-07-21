package gr.aueb.cf.bankApp.controllers;

import gr.aueb.cf.bankApp.interfaces.IPersistenceController;

import java.io.FileWriter;
import java.io.IOException;

public class FileIPersistenceController implements IPersistenceController {

    public String filePath;

    public FileIPersistenceController(String filePath) {
        this.filePath = filePath;
    }

    public void recordTransaction(String prefix, String transactionType, double amount, String iban) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            String transactionRecord = prefix + " - " + transactionType + ": Amount=" + amount + ", IBAN=" + iban;

            System.out.println(transactionRecord);

            writer.write(transactionRecord + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing transaction to file: " + e.getMessage());
        }
    }
}
