package com.task.repositories;


import com.task.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Integer> {
    List<Client> findAll();
    Client findById(int client_id);
}
