package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.TransferDto;

import java.util.List;

public interface TransferDao {

    void receiverTransaction(double amount, int receiverId);

    void senderTransaction(double amount, int senderId);

    List<TransferDto> transferHistory();

    TransferDto createTransaction(TransferDto transfer);

    TransferDto getTransfer(int transferId);
}
