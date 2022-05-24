package com.lendinglib.app.data;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserData
{

    String name;

    String phone;

    String customerId;
}
