package com.task.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "credit_offfer")
@Table(name = "credit_offer")
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private int id;
    @JoinColumn(name = "client_id")
    @OneToOne
    private Client client;
    @JoinColumn(name = "credit_id")
    @OneToOne
    private Credit credit;
    @Column(name = "credit_full_sum")
    private double sizeOfCredit;
    @Column(name = "duration_in_months")
    private int durationInMonths;

    @OneToMany(mappedBy = "creditOffer",fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Payment> payments;

    public CreditOffer(){}

    public CreditOffer(Client client, Credit credit, double sizeOfCredit, int durationInMonths, List<Payment> payments) {
        this.client = client;
        this.credit = credit;
        this.sizeOfCredit = sizeOfCredit;
        this.durationInMonths = durationInMonths;
        this.payments = payments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public double getSizeOfCredit() {
        return sizeOfCredit;
    }

    public void setSizeOfCredit(double sizeOfCredit) {
        this.sizeOfCredit = sizeOfCredit;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "CreditOffer{" +
                "id=" + id +
                ", client=" + client +
                ", credit=" + credit +
                ", sizeOfCredit=" + sizeOfCredit +
                ", durationInMonths=" + durationInMonths +
                ", payments=" + payments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditOffer)) return false;
        CreditOffer that = (CreditOffer) o;
        return getId() == that.getId() && Double.compare(that.getSizeOfCredit(), getSizeOfCredit()) == 0 && getDurationInMonths() == that.getDurationInMonths() && Objects.equals(getClient(), that.getClient()) && Objects.equals(getCredit(), that.getCredit()) && Objects.equals(getPayments(), that.getPayments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClient(), getCredit(), getSizeOfCredit(), getDurationInMonths(), getPayments());
    }
}
