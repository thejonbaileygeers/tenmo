package com.techelevator.tenmo.model;

public class TransferDto {


    private int transferId;
    private double transferAmount;
    private int fromAccount;
    private int toAccount;
    private int userId;


    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public int getTransferId() {return transferId;}

    public double getTransferAmount() {return transferAmount;}

    public int getFromAccount() {
        return fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    @Override
    public String toString() {
        return "Transfer {" +
                "transfer_id =" + transferId +
                ", amount ='" + transferAmount + '\'' +
                ", from_account =" + fromAccount +
                ", to_account =" + toAccount +
                '}';
    }
}
