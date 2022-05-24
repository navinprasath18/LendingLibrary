package com.lendinglib.app.service.impl;

import com.lendinglib.app.entities.Transaction;
import com.lendinglib.app.entities.User;
import com.lendinglib.app.exception.InvalidDataException;
import com.lendinglib.app.repo.TransactionRepo;
import com.lendinglib.app.repo.UserRepo;
import com.lendinglib.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepo repo;

    @Autowired TransactionRepo transactionRepo;

    @Override
    public User createUser (User data) throws InvalidDataException
    {
        validate(data);
        String cid= Long.toHexString(Double.doubleToLongBits(Math.random()));
        data.setCustomerId(cid);
        return repo.save(data);
    }

    @Override
    public User getUser (String customerId)
    {
       return repo.findByCustomerId(customerId);
    }

    @Override
    public List<User> getAllUsers ()
    {
        return repo.findAll();
    }

    @Override
    public User update (User user)
    {
        return repo.saveAndFlush(user);
    }

    public void validate(User data) throws InvalidDataException
    {
        if(data.getName()==null||data.getName().isEmpty())
        {
            throw new InvalidDataException();
        }
    }

    @Override
    public List<Transaction> findAllTransactionsByUser (String userId)
    {

        List<Transaction> list = transactionRepo.findByUserId(userId);
        return list;
    }
}
