package com.client.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.sample.model.Client;
import com.client.sample.repo.ClientRepo;

@Service
public class ClientService {

    @Autowired
    ClientRepo clientRepo;

    public void deleteByEmail(String email) {
        Client client = clientRepo.findByEmail(email);

        if (client != null) {
            clientRepo.delete(client);
        }
    }
}
