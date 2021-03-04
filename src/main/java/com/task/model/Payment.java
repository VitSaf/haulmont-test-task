package com.task.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "payments")
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;
    @Column(name = "payday")
    private Date payday;
    @Column(name = "sum_of_payment")
    private double sumOfPayment;
    @Column(name = "credit_part_of_payment")
    private double creditPartOfPayment;
    @Column(name = "rate_part_of_payment")
    private double ratePartOfPayment;



    public Payment(){}

    public Payment(Date payday, double sumOfPayment, double creditPartOfPayment, double ratePartOfPayment, CreditOffer creditOffer) {
        this.payday = payday;
        this.sumOfPayment = sumOfPayment;
        this.creditPartOfPayment = creditPartOfPayment;
        this.ratePartOfPayment = ratePartOfPayment;
        //this.creditOffer = creditOffer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }

    public double getSumOfPayment() {
        return sumOfPayment;
    }

    public void setSumOfPayment(double sumOfPayment) {
        this.sumOfPayment = sumOfPayment;
    }

    public double getCreditPartOfPayment() {
        return creditPartOfPayment;
    }

    public void setCreditPartOfPayment(double creditPartOfPayment) {
        this.creditPartOfPayment = creditPartOfPayment;
    }

    public double getRatePartOfPayment() {
        return ratePartOfPayment;
    }

    public void setRatePartOfPayment(double ratePartOfPayment) {
        this.ratePartOfPayment = ratePartOfPayment;
    }
//
//    //public CreditOffer getCreditOffer() {
//        return creditOffer;
//    }
//
//    public void setCreditOffer(CreditOffer creditOffer) {
//        this.creditOffer = creditOffer;
//    }

    @Override
    public String toString() {
        return  " Дата выплаты=" + payday + "\n"+
                ", Сумма=" + (creditPartOfPayment + ratePartOfPayment)+ "\n"+
                ", Выплата за кредит=" + creditPartOfPayment + "\n"+
                ", Выплата по процентам=" + ratePartOfPayment + "\n";
    }





}
