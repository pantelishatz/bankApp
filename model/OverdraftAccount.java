package gr.aueb.cf.bankApp.model;

import gr.aueb.cf.bankApp.exceptions.SsnNotValidException;

public class OverdraftAccount extends Account {

    public OverdraftAccount() {}

    public OverdraftAccount(String iban, User holder, double balance) {
        super(iban, holder, balance);
    }



    /**
     *
     * @param amount
     * @param ssn
     * @throws SsnNotValidException
     */
    @Override
    public void withdraw(double amount, String ssn) throws
            SsnNotValidException {
       try {
           if (!isSsnValid(ssn)) {
               throw new SsnNotValidException(ssn);
           }
           setBalance(getBalance() - amount);
       } catch (SsnNotValidException e) {
           System.err.println("Error in Ssn withdraw " + e);
           throw e;
       }
    }
}
