package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface AccountDao {

    Account getAccountBalanceAndId(int accountId);

    Account createAccount(Account newAccount);


}
