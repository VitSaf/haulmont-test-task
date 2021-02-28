package com.task.service;

import com.task.model.Client;
import com.task.model.CreditOffer;

import java.util.List;

public interface OfferService {
    List<CreditOffer> findAll();
    CreditOffer findById(int payment_id);
    List<CreditOffer> findByClient(Client client);
}
