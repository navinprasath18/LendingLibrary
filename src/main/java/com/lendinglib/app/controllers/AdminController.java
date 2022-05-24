package com.lendinglib.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendinglib.app.data.ItemData;
import com.lendinglib.app.data.UserData;
import com.lendinglib.app.entities.Item;
import com.lendinglib.app.service.ItemService;
import com.lendinglib.app.service.TransactionService;
import com.lendinglib.app.service.UserService;
import com.lendinglib.app.utils.LendingLibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LendingLibraryConstants.API_VERSION + LendingLibraryConstants.ADMIN)
public class AdminController
{
    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/createitem")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createItem(@RequestBody ItemData data) {

      Item item = itemService.createItem(data);
       return ResponseEntity.ok().body(item);
    }

    @GetMapping("/gellAllItems")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllItems() {
       return ResponseEntity.ok().body(itemService.getAll());
    }

    @GetMapping("/gellAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/gellAllTransaction")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllTransactions() {
        return ResponseEntity.ok().body(transactionService.getAllTransactions());
    }
}
