package com.task.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "bank")
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private int id;
    @Column(name = "bank_name")
    private String name;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "bank", orphanRemoval = true,cascade=CascadeType.ALL)
    private List<Client> clients;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "bank", orphanRemoval = true,cascade=CascadeType.ALL)
    private List<Credit> credits;

    public int getNumberOfClients(){
        return clients.size();
    }
    public int getNumberOfCredits(){
        return credits.size();
    }

    public Bank(){}

    public Bank(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank)) return false;
        Bank bank = (Bank) o;
        return getId() == bank.getId() && getName().equals(bank.getName());
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
