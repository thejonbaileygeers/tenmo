package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class Account {
    private int accountId;
    private double accountBalance;

    public Account() {
    }

    public Account(int accountId, double accountBalance) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
    }

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
                '}';
    }
}
