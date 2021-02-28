package com.task.repositories;



import com.task.model.Credit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends CrudRepository<Credit, Integer> {
    List<Credit> findAll();
    Credit findById(int credit_id);
}
