package com.lendinglib.app.repo;

import com.lendinglib.app.entities.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepo extends Baserepo<Transaction, String>
{
    Transaction findByUserIdAndItem(String user,String Item);
    List<Transaction> findByUserIdAndIsReturned(String user,Boolean isReturned);
    List<Transaction> findByUserIdAndIsPaid(String user,Boolean isReturned);
    List<Transaction>  findByUserId(String user);
}
