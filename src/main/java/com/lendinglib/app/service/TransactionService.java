package com.lendinglib.app.service;

import com.lendinglib.app.data.TransactionData;
import com.lendinglib.app.entities.Transaction;
import com.lendinglib.app.exception.InvalidDataException;

import java.util.List;

public interface TransactionService
{
    public Transaction rentItem(TransactionData data) throws InvalidDataException;
    public Transaction returnItem(TransactionData data) throws InvalidDataException;
    public List<Transaction> getUnReturnedByUser(String user_id)throws InvalidDataException;
    public List<Transaction> getAllTransactions();
    public List<Transaction> getAllOverDues(String user_id);

}
