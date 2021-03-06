package com.task.service;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.model.Credit;

import java.util.List;

public interface CreditService {
    List<Credit> getAll();
    Credit findById(int credit_id);
    Credit createCredit(Credit newCredit, Bank bank);
    Credit updateCredit(int creditId, double creditLimit, double creditRate);
    void removeCredit(int creditId);
    List<Credit> getByBank(Bank bank);

}
