package com.sg.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Statement {
    long idAccount;
    Integer amount;
    Integer balance;
    boolean success;
    OperationType operationType;
    LocalDateTime operationDateTime;

}
