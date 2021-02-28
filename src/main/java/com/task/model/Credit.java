package com.task.model;

import javax.persistence.*;

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
    @OneToOne
    private Bank bank;

    public Credit(){}

    public Credit(int id, double creditLimit, double rate, Bank bank) {
        this.id = id;
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
}
