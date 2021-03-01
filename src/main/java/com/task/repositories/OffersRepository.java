package com.task.repositories;

import com.task.model.Client;
import com.task.model.Credit;
import com.task.model.CreditOffer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OffersRepository extends CrudRepository<CreditOffer, Integer> {
    List<CreditOffer> findAll();
    CreditOffer findById(int offer_id);
    List<CreditOffer> findByClient(Client client);
    List<CreditOffer> findByClientAndCredit(Client client, Credit credit);
}
