package com.sg.controller;

import com.sg.domain.Account;
import com.sg.service.AccountManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountManager accountManager;

    public AccountController(AccountManager manageAccount){
        this.accountManager = manageAccount;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        Map<Long, Account> accounts = accountManager.findAll();

        if(accounts != null && !accounts.isEmpty()){
            return new ResponseEntity<>(new ArrayList<Account>(accounts.values()), HttpStatus.OK);
        }

        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") long id){
        Account account;
        Map<Long, Account> accounts = accountManager.findAll();

        if(accounts != null && !accounts.isEmpty()){
            account = accounts.get(id);
            if(account != null){
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        Account createdAccount = accountManager.createAccount(account);
        if(createdAccount != null){
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account){
        Account currentAccount = accountManager.findById(account.getId());

        if(currentAccount != null){
            Account updatedAccount = accountManager.updateAccount(account);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") long id){

        boolean isDeleteSuccess = accountManager.deleteAccount(id);

        if(isDeleteSuccess){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
