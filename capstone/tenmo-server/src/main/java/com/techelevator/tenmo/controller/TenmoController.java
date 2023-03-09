package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @RequestMapping(value = "/transfers/{id}", method = RequestMethod.POST)
    public Transfer authenticatedUserTransferTo(@PathVariable int id) {
        double receiversAccountBalance = new Account().getAccountBalance();
        double sendersAccountBalance = new Account().getAccountBalance();
        if ()
    }


}
