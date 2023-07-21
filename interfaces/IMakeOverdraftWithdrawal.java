package gr.aueb.cf.bankApp.interfaces;

import gr.aueb.cf.bankApp.model.OverdraftAccount;

public interface IMakeOverdraftWithdrawal {
    void makeOverdraftWithdrawal(OverdraftAccount account, Double amount);
}
