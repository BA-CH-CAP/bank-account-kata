package com.sg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Account {
    long id;
    String name;
    Integer balance;
    List<Statement> operationsHistory;
}
