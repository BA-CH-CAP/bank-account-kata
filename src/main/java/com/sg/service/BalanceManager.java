package com.sg.service;

import com.sg.domain.Statement;
import java.util.List;

public interface BalanceManager {

    Statement deposit(long idAccount, Integer amount);

    Statement withdraw(long idAccount, Integer amount);

    List<Statement> checkHistory(long idAccount);
}
