package com.task.service;

import com.task.model.Bank;
import com.task.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService{
    @Autowired
    BankRepository bankRepository;

    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll();
    }



    @Override
    public Bank findById(int bank_id) {
        return bankRepository.findById(bank_id);
    }

    @Override
    public Bank findByName(String bank_name) {
        return bankRepository.findByName(bank_name);
    }
}
