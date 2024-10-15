package com.sg.service;

import com.sg.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerTest {

    AccountManager accountManager;

    @BeforeEach
    public void init(){
        accountManager = new AccountManagerImpl();
    }

    @Test
    public void should_FindAllAccount(){
        //Given
        Account account = new Account(1L, "John Doe", 0, null);
        //When
        accountManager.createAccount(account);
        Map<Long, Account> accounts = accountManager.findAll();
        //Then
        assertFalse(accounts.isEmpty());
        assertEquals(1, accounts.size());
    }

    @Test
    public void should_FindAccount_When_IdParamIsProvided(){
        //Given
        Account account = new Account(1L, "John Doe", 0, null);
        //When
        accountManager.createAccount(account);
        Account retrievedAccount = accountManager.findById(1L);
        //Then
        assertNotNull(account);
        assertEquals(1L, retrievedAccount.getId());
        assertEquals("John Doe", retrievedAccount.getName());
    }

    @Test
    public void should_CreateAccount_When_newAccountIsProvided(){
        //Given
        Account account = new Account(1L, "John Doe", 0, null);
        //When
        var savedAccount = accountManager.createAccount(account);
        //Then
        assertNotNull(savedAccount);
        assertEquals(1L, savedAccount.getId());
    }

    @Test
    public void should_UpdateAccount_When_ModifyName(){
        //Given
        Account account = new Account(1L, "John Doe", 0, null);
        Account modifiedAccount = new Account(1L, "John Smith", null, null);
        //When
        accountManager.createAccount(account);
        Account updatedAccount = accountManager.updateAccount(modifiedAccount);
        //Then
        assertNotNull(updatedAccount);
        assertEquals(1L, updatedAccount.getId());
        assertEquals("John Smith", updatedAccount.getName());
        assertEquals(0, updatedAccount.getBalance());
    }

    @Test
    public void should_DeleteAccount_When_idParamIsProvided(){
        //Given
        Account account = new Account(1L, "John Doe", 0, null);
        //When
        accountManager.createAccount(account);
        boolean isAccountDeleted = accountManager.deleteAccount(1L);
        //Then
        assertTrue(isAccountDeleted);
    }

}