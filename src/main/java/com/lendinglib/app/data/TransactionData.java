package com.lendinglib.app.data;

import com.lendinglib.app.entities.TransactionType;
import lombok.Data;

import java.util.UUID;
@Data
public class TransactionData
{
    String userId;
    String item;
    String transactionType;
}
