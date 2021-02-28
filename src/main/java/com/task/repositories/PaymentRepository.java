package com.task.repositories;

import com.task.model.Credit;
import com.task.model.CreditOffer;
import com.task.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository  extends CrudRepository<Payment, Integer> {
    List<Payment> findAll();
    Payment findById(int payment_id);
    List<Payment> findByCreditOffer(CreditOffer creditOffer);
}
