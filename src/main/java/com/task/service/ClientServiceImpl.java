package com.task.service;


import com.task.model.Client;
import com.task.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepo;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepo){
        this.clientRepo = clientRepo;
    }

    @Override
    public Client getById(int clientId) {
        return clientRepo.findById(clientId);
    }

    @Override
    public List<Client> getAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepo.save(client);
    }
}
