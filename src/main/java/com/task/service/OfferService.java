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

    CreditOffer updateCreditOffer(int creditOfferId, CreditOffer updatedCreditOffer, Client client, Credit credit);
    void removeCreditOffer(int creditOfferId);

    List<CreditOffer> getByClientAndCredit(Client client, Credit credit);
    CreditOffer createOffer(int clientId, int creditId, int durationInMonths);
}
