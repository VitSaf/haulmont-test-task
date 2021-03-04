package com.task.service;

import com.task.model.Bank;
import com.task.model.Payment;
import com.task.model.CreditOffer;
import com.task.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAll();
    Payment getdById(int offer_id);
    List<Payment> getByCreditOffer(CreditOffer creditOffer);

    void createPayments(List<Payment> newPayments);//, CreditOffer offer);

}
