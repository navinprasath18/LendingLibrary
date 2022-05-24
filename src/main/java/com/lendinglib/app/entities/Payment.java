package com.lendinglib.app.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Table(name = "payments")
@Getter
@Setter
@Entity
public class Payment extends BaseEntity
{

    double amount;

    PaymentType payment_type;

    UUID userId;

    UUID transactionId;

    Instant payment_date;

    boolean isPaid;

    Instant paid_on;

}
