package com.task.utils;

import com.task.model.Payment;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentsCalculator {
    public static List<Payment> calcPayments(double creditSize, double rate, int durationInMonths){
        List payments= new ArrayList<Payment>();
        double creditSum = creditSize + creditSize*rate*(durationInMonths/12);
        double ratePartOfPayment = creditSize*rate*(durationInMonths/12)/durationInMonths;
        double creditPartOfPayment = creditSum/durationInMonths - ratePartOfPayment;
//        System.out.println("creditSum="+creditSum);
//        System.out.println("ratePartOfPayment="+ratePartOfPayment);
//        System.out.println("creditPartOfPayment="+creditPartOfPayment);
        System.out.println((ratePartOfPayment+creditPartOfPayment)*durationInMonths==creditSum);
        for(int i=0;i<durationInMonths;i++)
            payments.add(new Payment(Date.valueOf(LocalDate.now().plusMonths(i)),ratePartOfPayment+creditPartOfPayment,
                    creditPartOfPayment,ratePartOfPayment,null));
        return payments;
    }
}
