package com.lendinglib.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "users")
@Getter
@Setter
@Entity
public class User extends BaseEntity{

	

	String name;

	String phone;

	String customerId;

    int balance;

    public void addBalance(int rent)
    {
        this.balance= this.balance+rent;
    }

    public void deductBalance(int paid)
    {
        this.balance= this.balance-paid;
    }
}
