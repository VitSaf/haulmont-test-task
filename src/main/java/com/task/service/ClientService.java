package com.task.service;



import com.task.model.Client;

import java.util.List;

public interface ClientService {
    Client getById(int eventId);

    List<Client> getAll();

    Client createClient(Client client);
}
