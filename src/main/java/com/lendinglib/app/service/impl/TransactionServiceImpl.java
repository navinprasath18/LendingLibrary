package com.lendinglib.app.service.impl;

import com.lendinglib.app.data.TransactionData;
import com.lendinglib.app.entities.Item;
import com.lendinglib.app.entities.Transaction;
import com.lendinglib.app.entities.User;
import com.lendinglib.app.exception.InvalidDataException;
import com.lendinglib.app.exception.ItemUnavailableException;
import com.lendinglib.app.repo.TransactionRepo;
import com.lendinglib.app.service.ItemService;
import com.lendinglib.app.service.TransactionService;
import com.lendinglib.app.service.UserService;
import com.lendinglib.app.utils.LendingLibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService
{

    @Autowired UserService userService;

    @Autowired ItemService itemService;

    @Autowired
    TransactionRepo repo;

    @Override
    public Transaction rentItem (TransactionData data) throws InvalidDataException,ItemUnavailableException
    {
        validate(data);
        Transaction transaction = new Transaction();
        transaction.setItem(data.getItem());
        transaction.setUserId(data.getUserId());
        transaction.setRented_on(Instant.now());
        transaction.setIsPaid(Boolean.FALSE);
        transaction.setIsReturned(Boolean.FALSE);
        repo.save(transaction);
        UpdateUserAndInventoryOnRent(transaction);
        return transaction;
    }

    @Override
    public Transaction returnItem (TransactionData data) throws InvalidDataException
    {
        validate(data);
        Transaction transaction = repo.findByUserIdAndItem(data.getUserId(), data.getItem());
        transaction.setUserId(data.getUserId());
        transaction.setReturned_on(Instant.now());
        transaction.setIsReturned(Boolean.TRUE);
        UpdateUserAndInventoryOnReturn(transaction);
        repo.save(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getUnReturnedByUser (String user_id)
    {
        return repo.findByUserIdAndIsReturned(user_id,Boolean.FALSE);
    }

    @Override
    public List<Transaction> getAllTransactions ()
    {
        return repo.findAll();
    }

    @Override
    public List<Transaction> getAllOverDues (String user_id)
    {
        List<Transaction> list = getUnReturnedByUser(user_id);
        List<Transaction> listofiverdues = list.stream().filter(item->item.getRented_on().isBefore(Instant.now().minus(
            LendingLibraryConstants.DEFAULT_LENDING_DAYS, ChronoUnit.DAYS))).collect(
            Collectors.toList());
        return listofiverdues;
    }

    private boolean validate (TransactionData data) throws InvalidDataException
    {
        if (null == userService.getUser(data.getUserId())) {
            throw new InvalidDataException();
        }
        return true;
    }

    private void UpdateUserAndInventoryOnRent(Transaction transaction) throws ItemUnavailableException
    {
        Item item;
        synchronized(this) {
             item =  itemService.getItemByItemId(transaction.getItem());
            if (item.getCount_available()>0) {
                item.reduceCount();
            } else
            {
                throw new ItemUnavailableException();
            }
        }
        itemService.updateItem(item);
        User user = userService.getUser(transaction.getUserId());
        user.addBalance(item.getCostToRent());
        userService.update(user);

    }

    private void UpdateUserAndInventoryOnReturn(Transaction transaction)
    {
        Item item =  itemService.getItemByItemId(transaction.getItem());
        item.reduceCount();
        itemService.updateItem(item);
        User user = userService.getUser(transaction.getUserId());
        userService.update(user);

    }

}
