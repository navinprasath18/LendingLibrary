package com.lendinglib.app.controllers;

import com.lendinglib.app.data.TransactionData;

import com.lendinglib.app.entities.Transaction;
import com.lendinglib.app.exception.InvalidDataException;
import com.lendinglib.app.exception.ItemUnavailableException;
import com.lendinglib.app.service.TransactionService;
import com.lendinglib.app.utils.LendingLibraryConstants;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LendingLibraryConstants.API_VERSION + LendingLibraryConstants.TRANSACTION)
public class TransactionController
{
    @Autowired
    TransactionService service;

    @PostMapping("/rent")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity rent(@RequestBody TransactionData body){
        Transaction transaction = null;
        try {
            transaction = service.rentItem(body);
        }
        catch (InvalidDataException e) {
            ResponseEntity.badRequest().body("Invalid input data");
        }
        catch (ItemUnavailableException e) {
            ResponseEntity.badRequest().body("Item unavilable. Please refresh");
        }
        if(null==transaction)
        return  ResponseEntity.ok().body("Unable to rent Item.Try again");
        return ResponseEntity.ok().body(transaction);
    }

    @PostMapping("/return")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createUser(@RequestBody TransactionData body){
        Transaction transaction = null;
        try {
            transaction = service.returnItem(body);
        }
        catch (InvalidDataException e) {
            ResponseEntity.badRequest().body("Invalid input data");
        }
        if(null==transaction)
            return  ResponseEntity.ok().body("Unable to return Item.Try again");
        return ResponseEntity.ok().body(transaction);
    }




}
