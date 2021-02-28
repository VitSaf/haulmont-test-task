package com.task.repositories;

import com.task.model.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends CrudRepository<Bank, Integer> {
    List<Bank> findAll();
    Bank findById(int bank_id);
}
