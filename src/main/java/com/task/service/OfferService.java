package com.task.service;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.model.Credit;
import com.task.model.CreditOffer;

import java.util.List;

public interface OfferService {
    List<CreditOffer> findAll();
    CreditOffer findById(int payment_id);
    List<CreditOffer> getOffers(List<Client> clients);

    void removeCreditOffer(int creditOfferId);

    List<CreditOffer> getByClientAndCredit(Client client, Credit credit);
    CreditOffer createOffer(CreditOffer offer, Client client, Credit credit);




//    @Override
//    public Credit createCredit(Credit newCredit, Bank bank) {
//        newCredit.setBank(bank);
//        return creditRepository.save(newCredit);
//    }
}
