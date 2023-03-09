package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void receiverTransaction(double amount, int receiverId) {
        int senderId = new Transfer().getFromAccount();
        String sql = "UPDATE user_account " +
                "SET balance = balance + ? " +
                "WHERE account_id = ?; ";
        if (receiverId == senderId) {
            throw new IllegalArgumentException("Can't perform transaction");
        }
        jdbcTemplate.update(sql, amount, receiverId);
    }

    @Override
    public void senderTransaction(double amount, int senderId) {
        int receiverId = new Transfer().getToAccount();
        String sql = "UPDATE user_account " +
                "SET balance = balance - ? " +
                "WHERE account_id = ?; ";
        if (senderId == receiverId) {
            throw new IllegalArgumentException("Can't perform transaction");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        jdbcTemplate.update(sql, amount, senderId);
    }


    public List<Transfer> transferHistory() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, amount, transfer_to, transfer_from FROM transfers";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }

        return transfers;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferAmount(rs.getDouble("amount"));
        transfer.setToAccount(rs.getInt("transfer_to"));
        transfer.setFromAccount(rs.getInt("transfer_from"));
        return transfer;
    }


}








