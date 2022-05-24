package com.lendinglib.app.repo;

import com.lendinglib.app.entities.User;

import java.util.UUID;

public interface UserRepo  extends Baserepo<User, String>
{
    User findByCustomerId(String cid);
}
