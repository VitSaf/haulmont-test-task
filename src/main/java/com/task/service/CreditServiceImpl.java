package com.task.service;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.model.Credit;
import com.task.repositories.CreditRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService{
    @Autowired
    CreditRepository creditRepository;

    @Override
    public List<Credit> getAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit findById(int credit_id) {
        return creditRepository.findById(credit_id);
    }

    @Override
    public Credit createCredit(Credit newCredit, Bank bank) {
        newCredit.setBank(bank);
        return creditRepository.save(newCredit);
    }

    @Override
    public Credit updateCredit(int creditId, double creditLimit, double creditRate) {
        Credit credit = creditRepository.findById(creditId);
        credit.setCreditLimit(creditLimit);
        credit.setRate(creditRate);
        return creditRepository.save(credit);
    }


    @Override
    public void removeCredit(int creditId) {
        creditRepository.deleteById(creditId);
    }

    @Override
    public List<Credit> getByBank(Bank bank) {
        List result = new ArrayList<Credit>();
        for(Credit credit:creditRepository.findAll())
            if (credit.getBank().equals(bank)){
                result.add(credit);
            }
        return result;
    }
}
