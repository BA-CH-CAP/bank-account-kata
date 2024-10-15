package com.sg.controller;

import com.sg.domain.Statement;
import com.sg.service.BalanceManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceManager balanceManager;

    public BalanceController(BalanceManager balanceManager){
        this.balanceManager = balanceManager;
    }

    @GetMapping("/deposit")
    public ResponseEntity<Statement> deposit(@RequestParam(name ="id") long id, @RequestParam(name ="amount") Integer amount){
        Statement statement = balanceManager.deposit(id, amount);

        if(statement != null){
            return new ResponseEntity<>(statement, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/withdraw")
    public ResponseEntity<Statement> withdraw(@RequestParam(name ="id") long id, @RequestParam(name ="amount") Integer amount){
        Statement statement = balanceManager.withdraw(id, amount);

        if(statement != null){
            return new ResponseEntity<>(statement, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/check-history")
    public ResponseEntity<List<Statement>> checkHistory(@RequestParam(name ="id") long id){
        List<Statement> statements = balanceManager.checkHistory(id);

        if(statements != null){
            return new ResponseEntity<>(statements, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
