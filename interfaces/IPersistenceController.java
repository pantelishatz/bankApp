package gr.aueb.cf.bankApp.interfaces;

public interface IPersistenceController {
     void recordTransaction(String prefix, String transactionType, double amount, String iban);
}
