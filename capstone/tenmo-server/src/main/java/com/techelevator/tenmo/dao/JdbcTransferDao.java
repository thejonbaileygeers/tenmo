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
    public Transfer getTransfer(int transferId) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, amount, transfer_to, transfer_from " +
                "FROM transfers " +
                "WHERE city_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if(results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }


    @Override
    public Transfer createTransaction(Transfer transfer) {
        String sql = "INSERT INTO transfers (amount, transfer_to, transfer_from, tenmo_user.user_id) " +
                "INNER JOIN user_account ON user_account.account_id = transfers.transfer_from " +
                "INNER JOIN tenmo_user ON tenmo_user.user_id = user_account.user_id " +
                "VALUES (?, ?, ?, ?) RETURNING transfer_id;";
        Integer newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getTransferAmount(), transfer.getToAccount(), transfer.getFromAccount(), transfer.getFromAccount());

        return getTransfer(newTransferId);

    }


    @Override
    public void receiverTransaction(double amount, int receiverId) {
        String sql = "UPDATE user_account " +
                "SET balance = balance + ? " +
                "WHERE account_id = ?; ";
        jdbcTemplate.update(sql, amount, receiverId);
    }

    @Override
    public void senderTransaction(double amount, int senderId) {
        String sql = "UPDATE user_account " +
                "SET balance = balance - ? " +
                "WHERE account_id = ?; ";
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








