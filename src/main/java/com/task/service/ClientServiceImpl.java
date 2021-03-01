package com.task.service;


import com.task.model.Bank;
import com.task.model.Client;
import com.task.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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
    public Client updateClient(int id, String name, String phone, String mail, String passport) {
        Client client = clientRepo.findById(id);
        client.setFullName(name);
        client.setPhoneNumber(phone);
        client.setEmail(mail);
        client.setPassportNumber(passport);
        clientRepo.save(client);
        return client;
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
        List result = new  ArrayList<Client>();
        for(Client client:clientRepo.findAll())
            if (client.getBank().equals(bank)){
                result.add(client);
            }
        return result;
    }

    @Override
    public void removeClient(Client client) {
        clientRepo.delete(client);
    }


}
