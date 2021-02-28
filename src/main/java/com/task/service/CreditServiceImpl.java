package com.task.service;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.model.Credit;
import com.task.repositories.CreditRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Credit updateCredit(int creditId, Credit updatedCredit, Bank bank) {
        Credit credit = creditRepository.findById(creditId);

        BeanUtils.copyProperties(updatedCredit, credit, "id", "bank");

        if (!credit.getBank().equals(bank))
            credit.setBank(bank);

        return creditRepository.save(credit);
    }

    @Override
    public void removeCredit(int creditId) {
        creditRepository.deleteById(creditId);
    }
}
