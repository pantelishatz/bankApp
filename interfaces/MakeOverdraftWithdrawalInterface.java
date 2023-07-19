package gr.aueb.cf.bankApp.interfaces;

import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.OverdraftAccount;
import gr.aueb.cf.bankApp.model.User;

import java.util.Map;
import java.util.Scanner;

public interface MakeOverdraftWithdrawalInterface {
    void makeOverdraftWithdrawal(OverdraftAccount account, Double amount);
}
