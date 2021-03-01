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

import java.util.ArrayList;
import java.util.List;
@Service
public class OfferServiceImpl implements OfferService{
    @Autowired
    OffersRepository offersRepository;
    @Autowired
    PaymentService paymentService;
    @Autowired
    CreditService creditService;
    @Autowired
    ClientService clientService;




    @Override
    public List<CreditOffer> findAll() {
        return offersRepository.findAll();
    }

    @Override
    public CreditOffer findById(int offer_id) {
        return offersRepository.findById(offer_id);
    }

    @Override
    public List<CreditOffer> getOffers(List<Client> clients) {
        List<CreditOffer>  allOffers = offersRepository.findAll();
        List<CreditOffer> resultOffers = new ArrayList<>();
        for(Client client:clients)
            for(CreditOffer offer:allOffers)
                if (client.equals(offer.getClient()))
                    resultOffers.add(offer);
        return resultOffers;

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

    @Override
    public List<CreditOffer> getByClientAndCredit(Client client, Credit credit) {
        return offersRepository.findByClientAndCredit(client, credit);
    }

    //TODO!!!!!
    @Override
    public CreditOffer createOffer(int clientId, int creditId, int durationInMonths) {
        Credit credit = creditService.findById(creditId);
        CreditOffer co = new CreditOffer(clientService.getById(clientId), credit, credit.getCreditLimit(), durationInMonths,null);
//        System.out.println(co);
//        for(Payment p:payments)
//            p.setCreditOffer(co);
//        System.out.println(payments);
//        co.setPayments(payments);
//        paymentService.createPayments(payments);
        return offersRepository.save(co);
    }


}
