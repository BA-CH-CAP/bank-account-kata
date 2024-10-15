package com.sg.service;

import com.sg.domain.Account;
import java.util.Map;

public interface AccountManager {

    Map<Long, Account> findAll();

    Account findById(long l);

    Account createAccount(Account account);

    Account updateAccount(Account account);

    boolean deleteAccount(long id);
}
