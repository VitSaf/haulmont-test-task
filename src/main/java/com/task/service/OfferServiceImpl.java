package com.task.service;

import com.task.model.Client;
import com.task.model.CreditOffer;
import com.task.repositories.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OfferServiceImpl implements OfferService{
    @Autowired
    OffersRepository offersRepository;
    @Override
    public List<CreditOffer> findAll() {
        return offersRepository.findAll();
    }

    @Override
    public CreditOffer findById(int offer_id) {
        return offersRepository.findById(offer_id);
    }

    @Override
    public List<CreditOffer> findByClient(Client client) {
        return offersRepository.findByClient(client);
    }
}
