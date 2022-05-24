package com.lendinglib.app.entities;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Table(name = "transactions")
@Getter
@Setter
@Entity
public class Transaction extends BaseEntity{

    String userId;
    String item;
    Instant rented_on;
    Instant returned_on;
    Boolean isReturned;
    Boolean isPaid;

}
