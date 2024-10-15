package com.sg.service;

import com.sg.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AccountManagerImpl implements AccountManager {

    private static final Map<Long, Account> accounts = new HashMap<>();

    @Override
    public Map<Long, Account> findAll() {
        return accounts;
    }

    @Override
    public Account findById(long l) {
        return accounts.get(l);
    }

    @Override
    public Account createAccount(Account account) {
        //Check
        if(     account == null
                || account.getId() == 0
                || accounts.containsKey(account.getId())
                || account.getName() == null ){
            return null;
        }
        Integer accountBalance = account.getBalance();
        Integer balance = accountBalance == null ? 0 : accountBalance;
        account.setBalance(balance);
        //Save
        accounts.put(account.getId(), account);

        return accounts.get(account.getId());
    }

    /**
     * Update only the name of account
     * @param account
     * @return updated account
     */
    @Override
    public Account updateAccount(Account account) {
        Account savedAccount = accounts.get(account.getId());
        if(savedAccount == null){
            return null;
        }

        savedAccount.setName(account.getName());
        accounts.put(savedAccount.getId(), savedAccount);

        return accounts.get(account.getId());
    }

    @Override
    public boolean deleteAccount(long id){
        boolean isAccountDeleted = false;
        Account deletedAccount = accounts.remove(id);

        if(deletedAccount != null) {
            isAccountDeleted = true;
            log.info("Account id : {} -> deleted successfully.", id);
        }

        return isAccountDeleted;
    }
}
