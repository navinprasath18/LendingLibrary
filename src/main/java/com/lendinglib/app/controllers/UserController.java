package com.lendinglib.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendinglib.app.data.UserData;
import com.lendinglib.app.entities.User;
import com.lendinglib.app.exception.InvalidDataException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LendingLibraryConstants.API_VERSION + LendingLibraryConstants.USER)
public class UserController
{
    @Autowired UserService service;

    @Autowired TransactionService transactionService;

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createUser (@RequestBody UserData body)
    {

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(body, User.class);
        try {
            service.createUser(user);
        }
        catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body("Invalid Request body");
        }
        return ResponseEntity.ok().body("User Created");
    }

    @GetMapping("/findbyid")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getUser (@RequestParam(name = "id") String id)
    {
        User user = service.getUser(id);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.ok().body("User not found");
    }

    @GetMapping("/findAllTransactions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllTransactions (@RequestParam(name = "id") String id)
    {
        return ResponseEntity.ok().body(service.findAllTransactionsByUser(id));
    }

    @GetMapping("/findAllOverDues")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllOverDues (@RequestParam(name = "id") String id)
    {
            return ResponseEntity.ok().body(transactionService.getAllOverDues(id));

    }

    @GetMapping("/findAllRented")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAllRented(@RequestParam(name = "id") String id)
    {
        try {
            return ResponseEntity.ok().body(transactionService.getUnReturnedByUser(id));
        }
        catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body("Invalid user");
        }
    }

}
