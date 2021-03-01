package com.task.service;


import com.task.model.Bank;
import com.task.model.Client;
import com.task.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepo;


    @Override
    public Client getById(int clientId) {
        return clientRepo.findById(clientId);
    }

    @Override
    public Client createClient(Client newClient, Bank bank) {
        newClient.setBank(bank);
        return clientRepo.save(newClient);
    }

    @Override
    public Client updateClient(int clientId, Client updatedClient, Bank bank) {
        Client client = clientRepo.findById(clientId);

        BeanUtils.copyProperties(updatedClient, client, "id", "bank");

        if (!client.getBank().equals(bank))
            client.setBank(bank);

        return clientRepo.save(client);
    }

    @Override
    public void removeClient(int clientId) {
        clientRepo.deleteById(clientId);
    }

    @Override
    public List<Client> getAll() {
        return clientRepo.findAll();
    }

    @Override
    public List<Client> getByBank(Bank bank) {
        return clientRepo.findByBank(bank);
    }


}
