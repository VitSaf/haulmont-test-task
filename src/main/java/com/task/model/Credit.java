package com.task.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "credit")
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private int id;
    @Column(name = "credit_limit")
    private double creditLimit;
    @Column(name = "rate")
    private double rate;

    @JoinColumn(name = "bank_id")
    @ManyToOne
    private Bank bank;

    public Credit(){}

    public Credit(double creditLimit, double rate, Bank bank) {
        this.creditLimit = creditLimit;
        this.rate = rate;
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", creditLimit=" + creditLimit +
                ", rate=" + rate +
                ", bank=" + bank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credit)) return false;
        Credit credit = (Credit) o;
        return getId() == credit.getId() && Double.compare(credit.getCreditLimit(), getCreditLimit()) == 0 && Double.compare(credit.getRate(), getRate()) == 0 && getBank().equals(credit.getBank());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreditLimit(), getRate(), getBank());
    }
}
