package com.task.service;

import com.task.model.Credit;
import com.task.model.CreditOffer;
import com.task.model.Payment;
import com.task.repositories.PaymentRepository;
import org.springframework.beans.BeanUtils;
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


//    @Override
//    public Payment updatePayment(int paymentId, Payment updatedPayment, CreditOffer offer) {
//        Payment payment = paymentRepository.findById(paymentId);
//        BeanUtils.copyProperties(updatedPayment, payment, "id", "creditOffer");
//
//        if (!payment.getCreditOffer().equals(offer))
//            payment.setCreditOffer(offer);
//
//        return paymentRepository.save(payment);
//    }

    @Override
    public List<Payment> getByCreditOffer(CreditOffer creditOffer) {
        return paymentRepository.findByCreditOffer(creditOffer);
    }

    @Override
    public void createPayments(List<Payment> newPayments){//, CreditOffer offer) {
        for(Payment p : newPayments){
            //p.setCreditOffer(offer);
            paymentRepository.save(p);
        }

    }
}
