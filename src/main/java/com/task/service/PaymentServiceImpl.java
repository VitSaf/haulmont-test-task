package com.task.service;

import com.task.model.CreditOffer;
import com.task.model.Payment;
import com.task.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getdById(int payment_id) {
        return paymentRepository.findById(payment_id);
    }

    @Override
    public List<Payment> getByCreditOffer(CreditOffer creditOffer) {
        return paymentRepository.findByCreditOffer(creditOffer);
    }
}
