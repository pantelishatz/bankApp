package gr.aueb.cf.bankApp.model;


import gr.aueb.cf.bankApp.exceptions.InsufficientAmountException;
import gr.aueb.cf.bankApp.exceptions.InsufficientBalanceException;
import gr.aueb.cf.bankApp.exceptions.SsnNotValidException;

public class JointAccount extends IdentifiableEntity {
    private User firstHolder;
    private User secondHolder;
    private String iban;
    private double balance;

    public JointAccount() {}

    public JointAccount(User firstHolder, User secondHolder, String iban, double balance) {
        this.firstHolder = new User(firstHolder);
        this.secondHolder = new User(secondHolder);
        this.iban = iban;
        this.balance = balance;
    }

    public User getFirstHolder() {
        return new User(firstHolder);
    }

    public void setFirstHolder(User firstHolder) {
        this.firstHolder = new User(firstHolder);
    }

    public User getSecondHolder() {
        return new User(secondHolder);
    }

    public void setSecondHolder(User secondHolder) {
        this.secondHolder = new User(secondHolder);
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *
     * @param amount
     * @throws InsufficientAmountException
     */
    public void deposit(double amount) throws InsufficientAmountException {
        try {
            if (amount < 0) {
                throw new InsufficientAmountException(amount);
            }
            balance += amount;
        } catch (InsufficientAmountException e) {
            System.err.println("Error: Insufficient amount exception");
            throw e;
        }
    }

    public void withdraw(double amount, String ssn) throws InsufficientBalanceException, SsnNotValidException {
        try {
            if (!isSsnValid(ssn)) {
                throw new SsnNotValidException(ssn);
            }

            if (amount > balance) {
                throw new InsufficientBalanceException(balance, amount);
            }

            balance -= amount;

        } catch (InsufficientBalanceException | SsnNotValidException e) {
            System.err.println("Error in withdraw" + "\n" + e);
            throw e;
        }
    }

    public boolean isSsnValid(String ssn) {
        if ((ssn == null)) return false;

        return firstHolder.getSsn().equals(ssn) || secondHolder.getSsn().equals(ssn);
    }

    public double getAccountBalance() {
        return balance;
    }
}
