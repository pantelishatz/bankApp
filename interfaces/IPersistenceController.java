package gr.aueb.cf.bankApp.interfaces;
@FunctionalInterface
public interface IPersistenceController {
     void recordTransaction(String prefix, String transactionType, double amount, String iban);
}
