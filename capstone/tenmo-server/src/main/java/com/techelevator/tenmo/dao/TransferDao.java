package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    void receiverTransaction(double amount, int receiverId);

    void senderTransaction(double amount, int senderId);

    List<Transfer> transferHistory();

    Transfer createTransaction(Transfer transfer);

    Transfer getTransfer(int transferId);
}
