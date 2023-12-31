package gr.aueb.cf.bankApp.mocks;

import gr.aueb.cf.bankApp.interfaces.IPersistenceController;

import java.util.Optional;

public final class MockIPersistenceController implements IPersistenceController {

    public Optional<String> prefix = null;
    public Optional<String> transactionType = null;
    public Optional<Double> amount = null;
    public Optional<String> iban = null;

    public MockIPersistenceController() {
    }

    @Override
    public void recordTransaction(String prefix, String transactionType, double amount, String iban) {
        this.prefix = Optional.ofNullable(prefix);
        this.transactionType = Optional.ofNullable(transactionType);
        this.amount = Optional.ofNullable(amount);
        this.iban = Optional.ofNullable(iban);
    }
}