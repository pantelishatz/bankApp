package gr.aueb.cf.bankApp.model;

import gr.aueb.cf.bankApp.exceptions.SsnNotValidException;

public class OverdraftJointAccount extends JointAccount {

    public OverdraftJointAccount() {}

    public OverdraftJointAccount(User firstHolder, User secondHolder, String iban, double balance) {
        super(firstHolder, secondHolder, iban, balance);
    }

    @Override
    public void withdraw(double amount, String ssn) throws  SsnNotValidException {
        try {
            if (!isSsnValid(ssn)) {
                throw new SsnNotValidException(ssn);
            }

           setBalance(getBalance() - amount);
        } catch (SsnNotValidException e) {
            System.err.println("Error in Ssn" + "\n" + e);
            throw e;
        }
    }
}
