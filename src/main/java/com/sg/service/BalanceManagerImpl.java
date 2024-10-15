package com.sg.service;

import com.sg.domain.Account;

import com.sg.domain.OperationType;
import com.sg.domain.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BalanceManagerImpl implements BalanceManager{

    private final AccountManager accountManager;

    public BalanceManagerImpl(AccountManager accountManager){
        this.accountManager = accountManager;
    }

    @Override
    public Statement deposit(long idAccount, Integer amount) {
        Statement statement;
        Account account = accountManager.findById(idAccount);
        if(account == null){
            return null;
        }

        Integer currentBalance = account.getBalance();
        if (amount != null && amount > 0) {
            Integer newBalance = currentBalance + amount;
            account.setBalance(newBalance);

            statement = createStatement(idAccount, amount, newBalance, OperationType.DEPOSIT,true);
            log.info("New deposit was added successfully. Statement : {}", statement);

        }else{
            statement = createStatement(idAccount, amount, currentBalance, OperationType.DEPOSIT, false);
            log.warn("Failed to add new deposit! Statement : {}", statement);
        }

        saveOperation(account, statement);

        return statement;
    }

    @Override
    public Statement withdraw(long idAccount, Integer amount) {
        Statement statement;
        Account account = accountManager.findById(idAccount);
        if(account == null) {
            return null;
        }

        Integer currentBalance = account.getBalance();
        if(amount != null && amount > 0 && currentBalance >= amount){
            Integer newBalance = currentBalance - amount;
            account.setBalance(newBalance);

            statement = createStatement(idAccount, amount, newBalance, OperationType.WITHDRAW,true);
            log.info("New withdraw was done successfully. Statement : {}", statement);
        }else{
            statement = createStatement(idAccount, amount, currentBalance, OperationType.WITHDRAW, false);
            log.warn("Failed to withdraw! Statement : {}", statement);
        }

        saveOperation(account, statement);

        return statement;
    }

    @Override
    public List<Statement> checkHistory(long idAccount) {
        Account account = accountManager.findById(idAccount);
        if(account == null){
            return null;
        }

        List<Statement> statements = account.getOperationsHistory() ;
        return  statements == null ? List.of() : statements;
    }

    private static void saveOperation(Account account, Statement statement) {
        List<Statement> operationsList = account.getOperationsHistory();
        if(operationsList != null){
            operationsList.add(statement);
        }else{
            operationsList = new ArrayList<>();
            operationsList.add(statement);
            //Save
            account.setOperationsHistory(operationsList);
        }
    }

    private static Statement createStatement(long idAccount, Integer amount, Integer balance, OperationType opType, boolean isOpSuccess) {
        Statement statement = new Statement();
        statement.setIdAccount(idAccount);
        statement.setAmount(amount);
        statement.setBalance(balance);
        statement.setOperationType(opType);
        statement.setSuccess(isOpSuccess);
        statement.setOperationDateTime(LocalDateTime.now());
        return statement;
    }
}
