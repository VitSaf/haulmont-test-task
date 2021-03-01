package com.task.service;



import com.task.model.Bank;
import com.task.model.Client;

import java.util.List;

public interface ClientService {
    Client getById(int eventId);
    Client createClient(Client newClient, Bank bank);
    Client updateClient(int clientId, Client updatedClient, Bank bank);
    void removeClient(int clientId);
    List<Client> getAll();
    List<Client> getByBank(Bank bank);
}
