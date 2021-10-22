package main.model.wallet;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private int balance;
    private List<String> transactionHistory;

    public Wallet(int balance) {
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    protected boolean deposit (int amount, String description) {
        if (amount > 0){
            balance += amount;
            transactionHistory.add(description);
            return true;
        }
        return false;
    }

    protected boolean withdraw (int amount, String description){
        if (balance > amount){
            balance -= amount;
            transactionHistory.add(description);
            return true;
        }
        return false;
    }

    public int getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

}
