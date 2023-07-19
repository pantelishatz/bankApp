package gr.aueb.cf.bankApp.interfaces;

public interface PersistenceControllerInterface {
     void recordTransaction(String prefix, String transactionType, double amount, String iban);
}
