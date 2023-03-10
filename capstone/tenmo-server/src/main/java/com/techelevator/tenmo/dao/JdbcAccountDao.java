package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Account getAccountBalanceAndId(int accountId) {
        String sql = "SELECT account_id, balance FROM user-account WHERE account_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if (results.next()) {
            return mapRowToAccount(results);
        } else {
            return null;
        }
    }

    @Override
    public Account createAccount(Account newAccount) {
        String sql = "INSERT INTO user_account (balance, user_id) " +
                "VALUES (?, ?) RETURNING account_id;";
        Integer newAccountId = jdbcTemplate.queryForObject(sql, Integer.class, newAccount.getAccountBalance(), newAccount.getAccountId());

        return getAccountBalanceAndId(newAccountId);
    }


    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setAccountBalance(rs.getDouble(0));
        return account;
    }

}
