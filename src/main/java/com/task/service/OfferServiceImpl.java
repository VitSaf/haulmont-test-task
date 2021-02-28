package com.task.service;

import com.task.model.Client;
import com.task.model.Credit;
import com.task.model.CreditOffer;
import com.task.model.Payment;
import com.task.repositories.OffersRepository;
import com.task.utils.PaymentsCalculator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OfferServiceImpl implements OfferService{
    @Autowired
    OffersRepository offersRepository;
    @Autowired
    PaymentService paymentService;
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

    @Override
    public CreditOffer createCreditOffer(CreditOffer newCreditOffer, Client client, Credit credit) {
        newCreditOffer.setClient(client);
        newCreditOffer.setCredit(credit);
        List<Payment> payments = paymentService.createPayments(PaymentsCalculator.calcPayments(credit.getCreditLimit(), credit.getRate(), newCreditOffer.getDurationInMonths()),newCreditOffer);
        newCreditOffer.setPayments(payments);
        return offersRepository.save(newCreditOffer);
    }

    @Override
    public CreditOffer updateCreditOffer(int creditOfferId, CreditOffer updatedCreditOffer, Client client, Credit credit) {
        CreditOffer creditOffer = offersRepository.findById(creditOfferId);

        BeanUtils.copyProperties(updatedCreditOffer, creditOffer, "id", "credit","client","payments");

        if (!creditOffer.getCredit().equals(credit))
            creditOffer.setCredit(credit);
        if (!creditOffer.getClient().equals(client))
            creditOffer.setClient(client);

        return offersRepository.save(creditOffer);
    }

    @Override
    public void removeCreditOffer(int creditOfferId) {
        offersRepository.deleteById(creditOfferId);
    }
}
