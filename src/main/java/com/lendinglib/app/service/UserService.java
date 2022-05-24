package com.lendinglib.app.service;

import com.lendinglib.app.entities.Transaction;
import com.lendinglib.app.entities.User;
import com.lendinglib.app.exception.InvalidDataException;

import java.util.List;

public interface UserService
{

    User createUser(User data) throws InvalidDataException;

    User getUser(String customerId);

    List<User> getAllUsers();

    User update(User user);

    List<Transaction> findAllTransactionsByUser (String userId);
}
