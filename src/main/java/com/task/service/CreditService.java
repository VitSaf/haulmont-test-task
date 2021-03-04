package com.task.service;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.model.Credit;
import com.task.model.CreditOffer;

import java.util.List;

public interface CreditService {
    List<Credit> getAll();
    Credit findById(int credit_id);
    Credit createCredit(Credit newCredit, Bank bank);
    Credit updateCredit(int creditId, Credit updatedCredit, Bank bank, CreditOffer offer);
    void removeCredit(int creditId);
    List<Credit> getByBank(Bank bank);

}
