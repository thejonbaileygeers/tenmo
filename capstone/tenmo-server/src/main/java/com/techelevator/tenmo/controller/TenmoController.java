package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.TransferDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TenmoController {
    private final AccountDao accountDao;
    private final UserDao userDao;
    private final TransferDao transferDao;

    public TenmoController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

    @RequestMapping(value = "/user_account/{id}", method = RequestMethod.GET)
    public Account getAccountDetailsById(@PathVariable int id) {
        return accountDao.getAccountBalanceAndId(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/transfers", method = RequestMethod.POST)
    public TransferDto authenticatedUserTransferTo(@Valid @RequestBody TransferDto newTransfer) {
        int senderUserId = newTransfer.getUserId();
        int receiverUserId = newTransfer.getUserId();
        Account senderAccountId = accountDao.getAccountBalanceAndId(senderUserId);
        Account receiverAccountId = accountDao.getAccountBalanceAndId(receiverUserId);
        double amount = newTransfer.getTransferAmount();
        TransferDto finalTransfer = null;


        if (senderUserId != receiverUserId) {
            if (amount < senderAccountId.getAccountBalance() && amount > 0) {
                return transferDao.createTransaction(newTransfer);
        } else
            throw new IllegalArgumentException("Transaction cannot be completed.");
    }
        return transferDao.createTransaction(newTransfer);

    }



}
