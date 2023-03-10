package com.techelevator.tenmo.model;

public class Account {
    private int accountId;
    private double accountBalance;
    private int userId;

    public Account() {
    }

    public Account(int accountId, double accountBalance, int userId) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.userId = userId;
    }

    public int getUserId() {return userId; }

    public void setUserId(int userId) {this.userId = userId;}

    public int getAccountId() {
        return accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id =" + accountId +
                ", balance ='" + accountBalance +
                ", user =" + userId +
                '}';
    }
}
