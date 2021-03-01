package com.task.model;

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

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private CreditOffer creditOffer;

    public Payment(){}

    public Payment(Date payday, double sumOfPayment, double creditPartOfPayment, double ratePartOfPayment, CreditOffer creditOffer) {
        this.payday = payday;
        this.sumOfPayment = sumOfPayment;
        this.creditPartOfPayment = creditPartOfPayment;
        this.ratePartOfPayment = ratePartOfPayment;
        this.creditOffer = creditOffer;
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

    public CreditOffer getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }

    @Override
    public String toString() {
        return  " Дата выплаты=" + payday + "\n"+
                ", Сумма=" + (creditPartOfPayment + ratePartOfPayment)+ "\n"+
                ", Выплата за кредит=" + creditPartOfPayment + "\n"+
                ", Выплата по процентам=" + ratePartOfPayment + "\n";
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getId() == payment.getId() && Double.compare(payment.getSumOfPayment(), getSumOfPayment()) == 0 && Double.compare(payment.getCreditPartOfPayment(), getCreditPartOfPayment()) == 0 && Double.compare(payment.getRatePartOfPayment(), getRatePartOfPayment()) == 0 && Objects.equals(getPayday(), payment.getPayday()) && Objects.equals(getCreditOffer(), payment.getCreditOffer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPayday(), getSumOfPayment(), getCreditPartOfPayment(), getRatePartOfPayment(), getCreditOffer());
    }
}
