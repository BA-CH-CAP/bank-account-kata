package com.sg.service;

import com.sg.domain.Account;
import com.sg.domain.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BalanceManagerTest {

    AccountManager accountManager;
    BalanceManager balanceManager;

    @BeforeEach
    public void init(){
        accountManager = new AccountManagerImpl();
        balanceManager = new BalanceManagerImpl(accountManager);
    }

    @Test
    public void should_ReturnNewBalance_When_AddDepositIntoAccount(){
        //Given
        accountManager.createAccount(new Account(1L, "John Doe", 0, null));
        //When
        Statement statement = balanceManager.deposit(1L, 100);
        //Then
        assertTrue(statement.isSuccess());
        assertEquals(100, statement.getBalance());
    }

    @Test
    public void should_WithdrawFromAccount_When_AmountIsEqualOrLowerThanBalance(){
        //Given
        accountManager.createAccount(new Account(1L, "John Doe", 300, null));
        //When
        Statement statement = balanceManager.withdraw(1L, 100);
        //Then
        assertTrue(statement.isSuccess());
        assertEquals(200, statement.getBalance());
    }


    @Test
    public void should_ShowAccountHistory_When_IdParamaIsProvided(){
        //Given
        accountManager.createAccount(new Account(1L, "John Doe", 300, null));
        //When
        balanceManager.deposit(1L, 100);
        balanceManager.withdraw(1L, 200);
        List<Statement> statementList = balanceManager.checkHistory(1L);
        //Then
        assertNotNull(statementList);
        assertFalse(statementList.isEmpty());
        assertEquals(2, statementList.size());
    }
}