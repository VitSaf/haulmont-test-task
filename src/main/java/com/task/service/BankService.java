package com.task.service;

import com.task.model.Bank;

import java.util.List;

public interface BankService {
    List<Bank> getAll();
    Bank findById(int bank_id);
}
