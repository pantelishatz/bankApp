package gr.aueb.cf.bankApp.interfaces;

public interface IPersistenceControllerInterface {
     void recordTransaction(String prefix, String transactionType, double amount, String iban);
}
