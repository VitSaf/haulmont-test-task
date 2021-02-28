package com.task.repositories;

import com.task.model.Client;
import com.task.model.CreditOffer;
import com.task.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OffersRepository extends CrudRepository<CreditOffer, Integer> {
    List<CreditOffer> findAll();
    CreditOffer findById(int offer_id);
    List<CreditOffer> findByClient(Client client);
}
